package mpjp.client;

import java.io.IOException;
import java.util.Map;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;

import mpjp.shared.MPJPException;
import mpjp.shared.PieceStatus;
import mpjp.shared.PuzzleLayout;
import mpjp.shared.PuzzleView;
import mpjp.shared.geom.CurveTo;
import mpjp.shared.geom.LineTo;
import mpjp.shared.geom.PieceShape;
import mpjp.shared.geom.Point;
import mpjp.shared.geom.QuadTo;
import mpjp.shared.geom.Segment;

public class Solver extends Composite{

	/*--------------------------------------------------------------------*/
	/*------------------------ Variables ---------------------------------*/
	/*--------------------------------------------------------------------*/
	
	Canvas canvas;
    Context2d context;
    
    Integer selectedId;
	Integer selectedBlockId;
	Point start = null;
	Point delta, diff;
	
	public HandlerRegistration mousedown = null;
    public HandlerRegistration mouseup = null;
    public HandlerRegistration mousemove = null;
	
    /**
     *  ImageElement of the Puzzle to draw
     */
    ImageElement image = null;
    
    /**
     *  Height of canvas widget
     */
    static final float canvasHeight = 400;
    
    /**
     *  Width of canvas widget
     */
    static final float canvasWidth = 690;
    
    /**
     *  PuzzleView of the Puzzle to draw
     */
    PuzzleView puzzleView;
    
    /**
     *  PuzzleLayout of the Puzzle to draw
     */
    PuzzleLayout puzzleLayout;
    
    /**
     *  WorkspaceId of the Puzzle to draw
     */
    String workspaceId;
    
    /**
     *  Factor for scaling the workspace to the puzzleSpace
     */
    float factor;
    
    /**
     *  A remote service proxy to talk to the server-side Manager
     */
    public final ManagerServiceAsync managerService = 
    		GWT.create(ManagerService.class);
    
    
    /**
     *  Constructer to initialize the canvas widget
     */
    Solver(){
    	canvas = Canvas.createIfSupported();
    	
        canvas.setStyleName("mainCanvas");     
        canvas.setWidth(canvasWidth + "px");
        canvas.setCoordinateSpaceWidth((int) canvasWidth);
        canvas.setHeight(canvasHeight + "px");      
        canvas.setCoordinateSpaceHeight((int) canvasHeight);
        
        this.initWidget(canvas);
        
        context = canvas.getContext2d();
    }
    
    
	/*--------------------------------------------------------------------*/
	/*------------------------ Functions ---------------------------------*/
	/*--------------------------------------------------------------------*/
    
    /**
     *  Starts drawing process of Workspace
     * @param workspaceId of the Workspace to draw
     */
    public void drawPuzzle(String workspaceId) {
    	this.workspaceId=workspaceId;
    	try {
			managerService.getPuzzleView(workspaceId,
					new AsyncCallback<PuzzleView>() {
				public void onFailure(Throwable caught) {
					Webapps.console("Fail getPuzzleView:"+
							caught.getMessage());
				}
				public void onSuccess(PuzzleView pw) {
					puzzleView=pw;
					try {
						managerService.getCurrentLayout(workspaceId, 
								new AsyncCallback<PuzzleLayout>() {
							public void onFailure(Throwable caught) {
								Webapps.console("Fail getCurrentLayout:"+
										caught.getMessage());
							}
							public void onSuccess(PuzzleLayout pl) {
								puzzleLayout=pl;
								float widthFactor=canvasWidth/
										((float) puzzleView.getWorkspaceWidth());
								float heightFactor=canvasHeight/
										((float) puzzleView.getWorkspaceHeight());
								factor = Math.min(widthFactor, heightFactor);
								context.scale(factor, factor);
								loadImage();
							}
						});
					} catch (MPJPException | IOException e) {
						e.printStackTrace();
					}
				}
			});
		} catch (MPJPException | IOException e) {
			e.printStackTrace();
		}
    }
    
    /**
     *  Draws all pieces of given Workspace
     */
    private void drawPieces() {
    	Map<Integer,PieceStatus> pieces = puzzleLayout.getPieces();
    	for (int id:pieces.keySet()) {
    		Point location = pieces.get(id).getPosition();
    		double x = ((location.getX()));
    		double y = ((location.getY()));
			
    		context.translate(x, y);
    		context.setFillStyle("gray");
    		context.beginPath();
    		
    		drawPieceShape(puzzleView.getPieceShape(id));
    		
    		context.stroke();
    		context.fill();
    		context.closePath();
    		context.translate(-x, -y);
    		
    		//desenhar imagem só dá em forma de quadrado
    		double pw = puzzleView.getPieceWidth();
    		double ph = puzzleView.getPieceHeight();
    		Point p = puzzleView.getStandardPieceLocation(id);
    		
    		context.drawImage(image, p.getX()-pw/2, p.getY()-ph/2, pw, ph, 
    				x-pw/2, y-ph/2, pw, ph);
    	}
    	
    	HandlerRegistration mousedownhandler = canvas.addMouseDownHandler(
    			new MouseDownHandler() {
            @Override
            public void onMouseDown(MouseDownEvent event) { // mouse DOWN
            	float x = (float) event.getX()*(1/factor);
            	float y = (float) event.getY()*(1/factor);
        		start = new Point(x,y);
        		try {
        			managerService.selectPiece(workspaceId, start, 
        					new AsyncCallback<Integer>() {
        				public void onFailure(Throwable caught) {
        					Webapps.console("Fail selectPiece:"
        							+caught.getMessage());
        				}
        				public void onSuccess(Integer id) {
        					if(id == null)
        						selectedBlockId = null;
        					else {
        						PieceStatus piece = puzzleLayout.getPieces()
        														.get(id);
        						selectedId = id;
        						selectedBlockId = piece.getBlock();
        						delta = new Point(0,0);
        						diff  = new Point(x - piece.getX(),
        								y - piece.getY());
        					}					
        				}
        			});
        		} catch (MPJPException | IOException e1) {
        			e1.printStackTrace();
        		}
            }
        });
        
        HandlerRegistration mouseuphandler = canvas.addMouseUpHandler
        		(new MouseUpHandler() {
            @Override
            public void onMouseUp(MouseUpEvent event) { // mouse UP
            	float x = (float) event.getX()*(1/factor);
            	float y = (float) event.getY()*(1/factor);
        		if(selectedId != null) {
        			if(withinWorkspace(x,y)) {
        				double x2 = x - diff.getX();
        				double y2 = y - diff.getY();
        				Point point = new Point(x2,y2);
        				try {
        					managerService.connect(workspaceId, selectedId, 
        							point, new AsyncCallback<PuzzleLayout>() {
        						public void onFailure(Throwable caught) {
        							Webapps.console("Fail connect:"+
        									caught.getMessage());
        						}
        						public void onSuccess(PuzzleLayout pl) {
        							puzzleLayout=pl;
        							context.clearRect(0, 0, 
        									puzzleView.getWorkspaceWidth(),
        									puzzleView.getWorkspaceHeight());
        		        			drawPieces();
        		        			start = null;
        	        				selectedId = null;
        	        				selectedBlockId = null;
        						}
        					});
        				} catch (MPJPException | IOException e) {
        					Webapps.console("MPJPException");
        				}
        			} else {
        				Webapps.console("Out of workspace");
        			}
        		}
            }
        });
        
        HandlerRegistration mousemovehandler = canvas.addMouseMoveHandler(
        		new MouseMoveHandler() {
            @Override
            public void onMouseMove(MouseMoveEvent event) { // mouse MOVE
            	float x = (float) event.getX()*(1/factor);
            	float y = (float) event.getY()*(1/factor);
        		if(start != null) { 
        			if(withinWorkspace(x,y)) {
        				delta = new Point(x - start.getX(), y - start.getY());
        			}else {
        				Webapps.console("Out of Workspace");
        			}
        		}
            }
        });
        this.setHandlers(mousedownhandler, mouseuphandler, mousemovehandler);
    }
    
    /**
     *  Draws the shape of the given Piece
     * @param pieceShape
     */
    private void drawPieceShape(PieceShape pieceShape) {
    	Point start = pieceShape.getStartPoint();
    	
		context.moveTo(start.getX(), start.getY());
		
		for(Segment segment: pieceShape.getSegments()) {
			if(segment instanceof LineTo) {
				LineTo lineTo = (LineTo) segment;
				Point endPoint = lineTo.getEndPoint();
				
				context.lineTo(endPoint.getX(), 
						endPoint.getY());
			} else if(segment instanceof QuadTo) {
				QuadTo quadTo = (QuadTo) segment;
				Point endPoint = quadTo.getEndPoint();
				Point controlPoint = quadTo.getControlPoint();
				
				context.quadraticCurveTo(
						controlPoint.getX(),
						controlPoint.getY(),
						endPoint.getX(), 
						endPoint.getY());
			} else if(segment instanceof CurveTo) {
				CurveTo curveTo = (CurveTo) segment;
				Point endPoint = curveTo.getEndPoint();
				Point controlPoint1 = curveTo.getControlPoint1();
				Point controlPoint2 = curveTo.getControlPoint2();
				
				context.bezierCurveTo(
						controlPoint1.getX(),
						controlPoint1.getY(),
						controlPoint2.getX(),
						controlPoint2.getY(),
						endPoint.getX(),
						endPoint.getY());
			}
		}
    }
    
    /**
     *  Devia fazer um clear do canvas, mas nao funciona certo
     */
    public void clear() {
    	context.clearRect(0, 0, puzzleView.getWorkspaceWidth(), puzzleView.getWorkspaceHeight());
    	context.scale(1/factor, 1/factor);
    }
    
    /**
     *  Loads Image of given puzzleView
     */
    private void loadImage() {
		String imageName = puzzleView.getImage();
		if(imageName == null || "".equals(imageName)) 
			image = null;
		else {
			MPJPResources.loadImageElement(puzzleView.getImage(), i -> {
	  				image = i;
	  				drawPieces();
	  		});
		} 
	}
 
    /**
     *  Adds Mouse Handlers to this obeject
     * @param mousedownhandler
     * @param mouseuphandler
     * @param mousemovehandler
     */
    public void setHandlers(HandlerRegistration mousedownhandler, 
            HandlerRegistration mouseuphandler,
            HandlerRegistration mousemovehandler) {
        mousedown = mousedownhandler;
        mouseup = mouseuphandler;
        mousemove = mousemovehandler;
    }
    
	/**
	 * Checks if event is within this workspace. When dragging, events
	 * with coordinates outside the window can be sent to it.
	 * 
	 * @param coordinates to check
	 * @return {@code true} is event is within workspace, false otherwise 
	 */
	private boolean withinWorkspace(double x, double y) {
		int width  = (int) puzzleView.getWorkspaceWidth();
		int height = (int) puzzleView.getWorkspaceHeight(); 
		
		return x >= 0 && x <= width && y >= 0 && y <= height;
	}

}
