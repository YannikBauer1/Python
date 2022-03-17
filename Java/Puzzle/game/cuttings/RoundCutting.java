package mpjp.game.cuttings;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import mpjp.game.PuzzleStructure;
import mpjp.shared.geom.LineTo;
import mpjp.shared.geom.PieceShape;
import mpjp.shared.geom.Point;
import mpjp.shared.geom.QuadTo;

public class RoundCutting extends java.lang.Object
implements Cutting{

	public RoundCutting() {};
	
	@Override 
	public Map<Integer, PieceShape> getShapes(PuzzleStructure structure) {
		double pieceWidth=structure.getPieceWidth();
		double pieceHeight=structure.getPieceHeight();
		//int columns=structure.getColumns();
		//int rows=structure.getRows();
		Map<Integer, PieceShape> pieces = new HashMap<Integer, PieceShape>();
		//double topLeftX=-structure.getWidth()/2;
		//double topLeftY=structure.getHeight()/2;
		
	
		for(int i = 0; i<structure.getPieceCount(); i++) {
			//int quotient = i/columns;
			double startX = -pieceWidth/2;//topLeftX+(i%columns)*pieceWidth;
			double startY = pieceHeight/2;//topLeftY-(quotient)*pieceHeight;
			Point topLeft = new Point(startX,startY);
			Point topRight = new Point(startX+pieceWidth,startY);
			Point bottomRight = new Point(startX+pieceWidth,startY-pieceHeight);
			Point bottomLeft = new Point(startX,startY-pieceHeight);
			
			if(i==0) { //primeiro
				Point controlPointRight = controlPointVertical(bottomRight.getX(),bottomRight.getY(),topRight.getX(),topRight.getY(),pieceWidth);
				Point controlPointBottom = controlPointHorizontal(bottomLeft.getX(),bottomLeft.getY(),bottomRight.getX(),bottomRight.getY(),pieceHeight);
				System.out.print(bottomLeft);
				System.out.print(controlPointBottom);
				PieceShape piece = new PieceShape(topLeft)
						.addSegment(new LineTo(topRight))
						.addSegment(new QuadTo(controlPointRight,bottomRight))
						//.addSegment(new LineTo(bottomRight))
						.addSegment(new QuadTo(controlPointBottom,bottomLeft));
						//.addSegment(new LineTo(topLeft));
				pieces.put(i, piece);
				
			} /*else if(i<columns-1) { //primeira linha sem o ultimo
				Point controlPointRight = controlPointVertical(bottomRight.getX(),bottomRight.getY(),topRight.getX(),topRight.getY(),pieceWidth);
				Point controlPointBottom = controlPointHorizontal(bottomLeft.getX(),bottomLeft.getY(),bottomRight.getX(),bottomRight.getY(),pieceHeight);
				java.util.List<Segment> segments = pieces.get(i-1).getSegments();
				Point controlSegment = ((QuadTo) segments.get(1)).getControlPoint();
				
				PieceShape piece = new PieceShape(topLeft)
						.addSegment(new LineTo(topRight))
						.addSegment(new QuadTo(controlPointRight,bottomRight))
						.addSegment(new QuadTo(controlPointBottom,bottomLeft))
						.addSegment(new QuadTo(controlSegment,topLeft));
				pieces.put(i, piece);
				
			} else if (i==columns-1) { //ultimo da primeira linha
				Point controlPointBottom = controlPointHorizontal(bottomLeft.getX(),bottomLeft.getY(),bottomRight.getX(),bottomRight.getY(),pieceHeight);
				java.util.List<Segment> segments = pieces.get(i-1).getSegments();
				Point controlSegment = ((QuadTo) segments.get(1)).getControlPoint();
				
				PieceShape piece = new PieceShape(topLeft)
						.addSegment(new LineTo(topRight))
						.addSegment(new LineTo(bottomRight))
						.addSegment(new QuadTo(controlPointBottom,bottomLeft))
						.addSegment(new QuadTo(controlSegment,topLeft));
				pieces.put(i, piece);
				
			} else if (i==(rows-1)*columns) { //primeiro da ultima linha
				Point controlPointRight = controlPointVertical(bottomRight.getX(),bottomRight.getY(),topRight.getX(),topRight.getY(),pieceWidth);
				java.util.List<Segment> segments = pieces.get(i-1).getSegments();
				Point controlSegment = ((QuadTo) segments.get(2)).getControlPoint();
				
				PieceShape piece = new PieceShape(topLeft)
						.addSegment(new QuadTo(controlSegment,topRight))
						.addSegment(new QuadTo(controlPointRight,bottomRight))
						.addSegment(new LineTo(bottomLeft))
						.addSegment(new LineTo(topLeft));
				pieces.put(i, piece);
				
			} else if (i%columns==0) { //primeiro das linhas resto
				Point controlPointRight = controlPointVertical(bottomRight.getX(),bottomRight.getY(),topRight.getX(),topRight.getY(),pieceWidth);
				Point controlPointBottom = controlPointHorizontal(bottomLeft.getX(),bottomLeft.getY(),bottomRight.getX(),bottomRight.getY(),pieceHeight);
				java.util.List<Segment> segments = pieces.get(i-columns).getSegments();
				Point controlSegment = ((QuadTo) segments.get(2)).getControlPoint();
				
				PieceShape piece = new PieceShape(topLeft)
						.addSegment(new QuadTo(controlSegment,topRight))
						.addSegment(new QuadTo(controlPointRight,bottomRight))
						.addSegment(new QuadTo(controlPointBottom,bottomLeft))
						.addSegment(new LineTo(topLeft));
				pieces.put(i, piece);
				
			} else if (i==columns*rows-1) { //ultimo
				java.util.List<Segment> segmentsTop = pieces.get(i-columns).getSegments();
				Point controlSegmentTop = ((QuadTo) segmentsTop.get(2)).getControlPoint();
				java.util.List<Segment> segmentsLeft = pieces.get(i-1).getSegments();
				Point controlSegmentLeft = ((QuadTo) segmentsLeft.get(1)).getControlPoint();
				
				PieceShape piece = new PieceShape(topLeft)
						.addSegment(new QuadTo(controlSegmentTop,topRight))
						.addSegment(new LineTo(bottomRight))
						.addSegment(new LineTo(bottomLeft))
						.addSegment(new QuadTo(controlSegmentLeft,topLeft));
				pieces.put(i, piece);
				
			} else if (i%columns==columns-1) { //ultimos em cada linha resto
				Point controlPointBottom = controlPointHorizontal(bottomLeft.getX(),bottomLeft.getY(),bottomRight.getX(),bottomRight.getY(),pieceHeight);
				java.util.List<Segment> segmentsTop = pieces.get(i-columns).getSegments();
				Point controlSegmentTop = ((QuadTo) segmentsTop.get(2)).getControlPoint();
				java.util.List<Segment> segmentsLeft = pieces.get(i-1).getSegments();
				Point controlSegmentLeft = ((QuadTo) segmentsLeft.get(1)).getControlPoint();
				
				PieceShape piece = new PieceShape(topLeft)
						.addSegment(new QuadTo(controlSegmentTop,topRight))
						.addSegment(new LineTo(bottomRight))
						.addSegment(new QuadTo(controlPointBottom,bottomLeft))
						.addSegment(new QuadTo(controlSegmentLeft,topLeft));
				pieces.put(i, piece);
				
			} else if (i>(rows-1)*columns) { //ultima linha resto
				Point controlPointRight = controlPointVertical(bottomRight.getX(),bottomRight.getY(),topRight.getX(),topRight.getY(),pieceWidth);
				java.util.List<Segment> segmentsTop = pieces.get(i-columns).getSegments();
				Point controlSegmentTop = ((QuadTo) segmentsTop.get(2)).getControlPoint();
				java.util.List<Segment> segmentsLeft = pieces.get(i-1).getSegments();
				Point controlSegmentLeft = ((QuadTo) segmentsLeft.get(1)).getControlPoint();
				
				PieceShape piece = new PieceShape(topLeft)
						.addSegment(new QuadTo(controlSegmentTop,topRight))
						.addSegment(new QuadTo(controlPointRight,bottomRight))
						.addSegment(new LineTo(bottomLeft))
						.addSegment(new QuadTo(controlSegmentLeft,topLeft));
				pieces.put(i, piece);
				
			} else { //resto
				Point controlPointRight = controlPointVertical(bottomRight.getX(),bottomRight.getY(),topRight.getX(),topRight.getY(),pieceWidth);
				Point controlPointBottom = controlPointHorizontal(bottomLeft.getX(),bottomLeft.getY(),bottomRight.getX(),bottomRight.getY(),pieceHeight);
				java.util.List<Segment> segmentsTop = pieces.get(i-columns).getSegments();
				Point controlSegmentTop = ((QuadTo) segmentsTop.get(2)).getControlPoint();
				java.util.List<Segment> segmentsLeft = pieces.get(i-1).getSegments();
				Point controlSegmentLeft = ((QuadTo) segmentsLeft.get(1)).getControlPoint();
				
				PieceShape piece = new PieceShape(topLeft)
						.addSegment(new QuadTo(controlSegmentTop,topRight))
						.addSegment(new QuadTo(controlPointRight,bottomRight))
						.addSegment(new QuadTo(controlPointBottom,bottomLeft))
						.addSegment(new QuadTo(controlSegmentLeft,topLeft));
				pieces.put(i, piece);
			}*/ else {
				PieceShape piece = new PieceShape(topLeft)
						.addSegment(new LineTo(new Point(startX+pieceWidth,startY)))
						.addSegment(new LineTo(new Point(startX+pieceWidth,startY-pieceHeight)))
						.addSegment(new LineTo(new Point(startX,startY-pieceHeight)))
						.addSegment(new LineTo(topLeft));
				pieces.put(i, piece);
			}
		}
		return pieces;
	}
	
	public Point controlPointHorizontal(double x1, double y1, double x2, double y2, double pieceHeight) { //x1<x2 e y1=y2
		Random r1 = new Random();
		double x = x1 + (x2 - x1) * r1.nextDouble();
		double yRange = (Math.min(x-x1,x2-x)*(pieceHeight/2))/((x2-x1)/2)/2;
		Random r2 = new Random();
		double y = y1-yRange + (y1-yRange - (y1+yRange)) * r2.nextDouble();
		return new Point(x,y);
	}
	public Point controlPointVertical(double x1, double y1, double x2, double y2, double pieceWidth) { //y1<y2 e x1=x2
		Random r1 = new Random();
		double y = y1 + (y2 - y1) * r1.nextDouble();
		double xRange = (Math.min(y-y1,y2-y)*(pieceWidth/2))/((y2-y1)/2)/2;
		Random r2 = new Random();
		double x = x1-xRange + (x1-xRange - (x1+xRange)) * r2.nextDouble();
		return new Point(x,y);
	}
	
}
