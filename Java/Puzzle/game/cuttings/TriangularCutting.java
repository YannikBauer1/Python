package mpjp.game.cuttings;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import mpjp.game.PuzzleStructure;
import mpjp.shared.geom.LineTo;
import mpjp.shared.geom.PieceShape;
import mpjp.shared.geom.Point;
import mpjp.shared.geom.Segment;

public class TriangularCutting extends java.lang.Object
implements Cutting {

	public TriangularCutting() {};
	
	/**
	 *
	 */
	@Override
	public Map<Integer, PieceShape> getShapes(PuzzleStructure structure) {
		double pieceWidth=structure.getPieceWidth();
		double pieceHeight=structure.getPieceHeight();
		int columns=structure.getColumns();
		int rows=structure.getRows();
		Map<Integer, PieceShape> pieces = new HashMap<Integer, PieceShape>();
		
		for(int i = 0; i<structure.getPieceCount(); i++) {
			double startX = -pieceWidth/2;
			double startY = -pieceHeight/2;
			Point topLeft = new Point(startX,startY);
			Point topRight = new Point(startX+pieceWidth,startY);
			Point bottomRight = new Point(startX+pieceWidth,startY+pieceHeight);
			Point bottomLeft = new Point(startX,startY+pieceHeight);
			if(i==0) { //primeiro
				Point triangularRight = triangularPointVertical(topRight.getX(),topRight.getY(),bottomRight.getX(),bottomRight.getY(),pieceWidth);
				Point triangularBottom = triangularPointHorizontal(bottomLeft.getX(),bottomLeft.getY(),bottomRight.getX(),bottomRight.getY(),pieceHeight);
				//System.out.print(triangularRight);
				PieceShape piece = new PieceShape(topLeft)
						.addSegment(new LineTo(topRight))
						.addSegment(new LineTo(triangularRight))
						.addSegment(new LineTo(bottomRight))
						.addSegment(new LineTo(triangularBottom))
						.addSegment(new LineTo(bottomLeft))
						.addSegment(new LineTo(topLeft));
				pieces.put(i, piece);
				
			} else if(i<columns-1) { //primeira linha sem o ultimo
				Point triangularRight = triangularPointVertical(topRight.getX(),topRight.getY(),bottomRight.getX(),bottomRight.getY(),pieceWidth);
				Point triangularBottom = triangularPointHorizontal(bottomLeft.getX(),bottomLeft.getY(),bottomRight.getX(),bottomRight.getY(),pieceHeight);
				java.util.List<Segment> segments = pieces.get(i-1).getSegments();
				Point segemtControlPoint = ((LineTo) segments.get(1)).getEndPoint();
				Point triangularLeft = new Point(segemtControlPoint.getX(),segemtControlPoint.getY());
				triangularLeft.setX(triangularLeft.getX()-pieceWidth);
				PieceShape piece = new PieceShape(topLeft)
						.addSegment(new LineTo(topRight))
						.addSegment(new LineTo(triangularRight))
						.addSegment(new LineTo(bottomRight))
						.addSegment(new LineTo(triangularBottom))
						.addSegment(new LineTo(bottomLeft))
						.addSegment(new LineTo(triangularLeft))
						.addSegment(new LineTo(topLeft));
				pieces.put(i, piece);
				
			} else if (i==columns-1) { //ultimo da primeira linha
				Point triangularBottom = triangularPointHorizontal(bottomLeft.getX(),bottomLeft.getY(),bottomRight.getX(),bottomRight.getY(),pieceHeight);
				java.util.List<Segment> segments = pieces.get(i-1).getSegments();
				Point segemtControlPoint = ((LineTo) segments.get(1)).getEndPoint();
				Point triangularLeft = new Point(segemtControlPoint.getX(),segemtControlPoint.getY());
				triangularLeft.setX(triangularLeft.getX()-pieceWidth);
				PieceShape piece = new PieceShape(topLeft)
						.addSegment(new LineTo(topRight))
						.addSegment(new LineTo(bottomRight))
						.addSegment(new LineTo(triangularBottom))
						.addSegment(new LineTo(bottomLeft))
						.addSegment(new LineTo(triangularLeft))
						.addSegment(new LineTo(topLeft));
				pieces.put(i, piece);
				
			} else if (i==(rows-1)*columns) { //primeiro da ultima linha
				Point triangularRight = triangularPointVertical(topRight.getX(),topRight.getY(),bottomRight.getX(),bottomRight.getY(),pieceWidth);
				java.util.List<Segment> segments = pieces.get(i-columns).getSegments();
				Point segemtControlPoint = ((LineTo) segments.get(segments.size()-3)).getEndPoint();
				Point triangularTop = new Point(segemtControlPoint.getX(),segemtControlPoint.getY());
				triangularTop.setY(triangularTop.getY()-pieceHeight);
				
				PieceShape piece = new PieceShape(topLeft)
						.addSegment(new LineTo(triangularTop))
						.addSegment(new LineTo(topRight))
						.addSegment(new LineTo(triangularRight))
						.addSegment(new LineTo(bottomRight))
						.addSegment(new LineTo(bottomLeft))
						.addSegment(new LineTo(topLeft));
				pieces.put(i, piece);
				
			} else if (i%columns==0) { //primeiro das linhas
				Point triangularRight = triangularPointVertical(topRight.getX(),topRight.getY(),bottomRight.getX(),bottomRight.getY(),pieceWidth);
				Point triangularBottom = triangularPointHorizontal(bottomLeft.getX(),bottomLeft.getY(),bottomRight.getX(),bottomRight.getY(),pieceHeight);
				java.util.List<Segment> segments = pieces.get(i-columns).getSegments();
				Point segemtControlPoint = ((LineTo) segments.get(segments.size()-3)).getEndPoint();
				Point triangularTop = new Point(segemtControlPoint.getX(),segemtControlPoint.getY());
				triangularTop.setY(triangularTop.getY()-pieceHeight);
				
				PieceShape piece = new PieceShape(topLeft)
						.addSegment(new LineTo(triangularTop))
						.addSegment(new LineTo(topRight))
						.addSegment(new LineTo(triangularRight))
						.addSegment(new LineTo(bottomRight))
						.addSegment(new LineTo(triangularBottom))
						.addSegment(new LineTo(bottomLeft))
						.addSegment(new LineTo(topLeft));
				pieces.put(i, piece);
				
			} else if (i==columns*rows-1) { //ultimo
				java.util.List<Segment> segmentsTop = pieces.get(i-columns).getSegments();
				java.util.List<Segment> segmentsLeft = pieces.get(i-1).getSegments();
				Point segemtControlPointTop = ((LineTo) segmentsTop.get(3)).getEndPoint();
				Point triangularTop = new Point(segemtControlPointTop.getX(),segemtControlPointTop.getY());
				triangularTop.setY(triangularTop.getY()-pieceHeight);
				Point segemtControlPointLeft = ((LineTo) segmentsLeft.get(2)).getEndPoint();
				Point triangularLeft = new Point(segemtControlPointLeft.getX(),segemtControlPointLeft.getY());
				triangularLeft.setX(triangularLeft.getX()-pieceWidth);
				
				PieceShape piece = new PieceShape(topLeft)
						.addSegment(new LineTo(triangularTop))
						.addSegment(new LineTo(topRight))
						.addSegment(new LineTo(bottomRight))
						.addSegment(new LineTo(bottomLeft))
						.addSegment(new LineTo(triangularLeft))
						.addSegment(new LineTo(topLeft));
				pieces.put(i, piece);
				
			} else if (i==columns*2-1) { //ultimo
				java.util.List<Segment> segmentsTop = pieces.get(i-columns).getSegments();
				java.util.List<Segment> segmentsLeft = pieces.get(i-1).getSegments();
				Point segemtControlPointTop = ((LineTo) segmentsTop.get(2)).getEndPoint();
				Point triangularTop = new Point(segemtControlPointTop.getX(),segemtControlPointTop.getY());
				triangularTop.setY(triangularTop.getY()-pieceHeight);
				Point segemtControlPointLeft = ((LineTo) segmentsLeft.get(2)).getEndPoint();
				Point triangularLeft = new Point(segemtControlPointLeft.getX(),segemtControlPointLeft.getY());
				triangularLeft.setX(triangularLeft.getX()-pieceWidth);
				
				PieceShape piece = new PieceShape(topLeft)
						.addSegment(new LineTo(triangularTop))
						.addSegment(new LineTo(topRight))
						.addSegment(new LineTo(bottomRight))
						.addSegment(new LineTo(bottomLeft))
						.addSegment(new LineTo(triangularLeft))
						.addSegment(new LineTo(topLeft));
				pieces.put(i, piece);
				
			}else if (i%columns==columns-1) { //ultimos em cada linha
				Point triangularBottom = triangularPointHorizontal(bottomLeft.getX(),bottomLeft.getY(),bottomRight.getX(),bottomRight.getY(),pieceHeight);
				java.util.List<Segment> segmentsTop = pieces.get(i-columns).getSegments();
				java.util.List<Segment> segmentsLeft = pieces.get(i-1).getSegments();
				Point segemtControlPointTop = ((LineTo) segmentsTop.get(3)).getEndPoint();
				Point triangularTop = new Point(segemtControlPointTop.getX(),segemtControlPointTop.getY());
				triangularTop.setY(triangularTop.getY()-pieceHeight);
				Point segemtControlPointLeft = ((LineTo) segmentsLeft.get(2)).getEndPoint();
				Point triangularLeft = new Point(segemtControlPointLeft.getX(),segemtControlPointLeft.getY());
				triangularLeft.setX(triangularLeft.getX()-pieceWidth);
				
				PieceShape piece = new PieceShape(topLeft)
						.addSegment(new LineTo(triangularTop))
						.addSegment(new LineTo(topRight))
						.addSegment(new LineTo(bottomRight))
						.addSegment(new LineTo(triangularBottom))
						.addSegment(new LineTo(bottomLeft))
						.addSegment(new LineTo(triangularLeft))
						.addSegment(new LineTo(topLeft));
				pieces.put(i, piece);
				
			} else if (i>(rows-1)*columns) { //ultima linha
				Point triangularRight = triangularPointVertical(topRight.getX(),topRight.getY(),bottomRight.getX(),bottomRight.getY(),pieceWidth);
				java.util.List<Segment> segmentsTop = pieces.get(i-columns).getSegments();
				java.util.List<Segment> segmentsLeft = pieces.get(i-1).getSegments();
				Point segemtControlPointTop = ((LineTo) segmentsTop.get(4)).getEndPoint();
				Point triangularTop = new Point(segemtControlPointTop.getX(),segemtControlPointTop.getY());
				triangularTop.setY(triangularTop.getY()-pieceHeight);
				Point segemtControlPointLeft = ((LineTo) segmentsLeft.get(2)).getEndPoint();
				Point triangularLeft = new Point(segemtControlPointLeft.getX(),segemtControlPointLeft.getY());
				triangularLeft.setX(triangularLeft.getX()-pieceWidth);
				
				PieceShape piece = new PieceShape(topLeft)
						.addSegment(new LineTo(triangularTop))
						.addSegment(new LineTo(topRight))
						.addSegment(new LineTo(triangularRight))
						.addSegment(new LineTo(bottomRight))
						.addSegment(new LineTo(bottomLeft))
						.addSegment(new LineTo(triangularLeft))
						.addSegment(new LineTo(topLeft));
				pieces.put(i, piece);
				
			} else if (i<columns*2){ //resto
				Point triangularRight = triangularPointVertical(topRight.getX(),topRight.getY(),bottomRight.getX(),bottomRight.getY(),pieceWidth);
				Point triangularBottom = triangularPointHorizontal(bottomLeft.getX(),bottomLeft.getY(),bottomRight.getX(),bottomRight.getY(),pieceHeight);
				java.util.List<Segment> segmentsTop = pieces.get(i-columns).getSegments();
				java.util.List<Segment> segmentsLeft = pieces.get(i-1).getSegments();
				Point segemtControlPointTop = ((LineTo) segmentsTop.get(3)).getEndPoint();
				Point triangularTop = new Point(segemtControlPointTop.getX(),segemtControlPointTop.getY());
				triangularTop.setY(triangularTop.getY()-pieceHeight);
				Point segemtControlPointLeft = ((LineTo) segmentsLeft.get(2)).getEndPoint();
				Point triangularLeft = new Point(segemtControlPointLeft.getX(),segemtControlPointLeft.getY());
				triangularLeft.setX(triangularLeft.getX()-pieceWidth);
				
				PieceShape piece = new PieceShape(topLeft)
						.addSegment(new LineTo(triangularTop))
						.addSegment(new LineTo(topRight))
						.addSegment(new LineTo(triangularRight))
						.addSegment(new LineTo(bottomRight))
						.addSegment(new LineTo(triangularBottom))
						.addSegment(new LineTo(bottomLeft))
						.addSegment(new LineTo(triangularLeft))
						.addSegment(new LineTo(topLeft));
				pieces.put(i, piece);
			}else { //resto
				Point triangularRight = triangularPointVertical(topRight.getX(),topRight.getY(),bottomRight.getX(),bottomRight.getY(),pieceWidth);
				Point triangularBottom = triangularPointHorizontal(bottomLeft.getX(),bottomLeft.getY(),bottomRight.getX(),bottomRight.getY(),pieceHeight);
				java.util.List<Segment> segmentsTop = pieces.get(i-columns).getSegments();
				java.util.List<Segment> segmentsLeft = pieces.get(i-1).getSegments();
				Point segemtControlPointTop = ((LineTo) segmentsTop.get(4)).getEndPoint();
				Point triangularTop = new Point(segemtControlPointTop.getX(),segemtControlPointTop.getY());
				triangularTop.setY(triangularTop.getY()-pieceHeight);
				Point segemtControlPointLeft = ((LineTo) segmentsLeft.get(2)).getEndPoint();
				Point triangularLeft = new Point(segemtControlPointLeft.getX(),segemtControlPointLeft.getY());
				triangularLeft.setX(triangularLeft.getX()-pieceWidth);
				
				PieceShape piece = new PieceShape(topLeft)
						.addSegment(new LineTo(triangularTop))
						.addSegment(new LineTo(topRight))
						.addSegment(new LineTo(triangularRight))
						.addSegment(new LineTo(bottomRight))
						.addSegment(new LineTo(triangularBottom))
						.addSegment(new LineTo(bottomLeft))
						.addSegment(new LineTo(triangularLeft))
						.addSegment(new LineTo(topLeft));
				pieces.put(i, piece);
			}
		}
		return pieces;
	};
	public Point triangularPointHorizontal(double x1, double y1, double x2, double y2, double pieceHeight) { //x1<x2 e y1=y2
		Random r1 = new Random();
		double margin = Math.abs(x1-x2)/4;
		double x = x1+margin + (x2-margin - (x1+margin)) * r1.nextDouble();
		//double yRange = ((Math.min(Math.abs(x-x1),Math.abs(x2-x))*(pieceHeight/2))/((x2-x1)/2))/2;
		double yRange = pieceHeight/4;
		Random r2 = new Random();
		double y = y1+yRange + (y2-yRange - (y1+yRange)) * r2.nextDouble();
		return new Point(x,y);
	}
	public Point triangularPointVertical(double x1, double y1, double x2, double y2, double pieceWidth) { //y1<y2 e x1=x2
		Random r1 = new Random();
		double margin = Math.abs(y1-y2)/4;
		double y = y1+margin + (y2-margin - (y1+margin)) * r1.nextDouble();
		//double xRange = ((Math.min(Math.abs(y-y1),Math.abs(y2-y))*(pieceWidth/2))/((y2-y1)/2))/2;
		double xRange = pieceWidth/4;
		Random r2 = new Random();
		double x = x1+xRange + (x2-xRange - (x1+xRange)) * r2.nextDouble();
		return new Point(x,y);
	}
}
