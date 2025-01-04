import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public int getNumPoints (Shape s) {
        // Put code here
        int PointNums = 0;
        
        for (Point currPt : s.getPoints()) {
            PointNums = PointNums + 1;
        }
        return PointNums;
    }

    public double getAverageLength(Shape s) {
        // Put code here
        int pointNums = 0;
        double totalPerim = 0.0;
        Point prevPt = s.getLastPoint();
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
            
            pointNums = pointNums + 1;
        }
        return totalPerim / pointNums;
    }

    public double getLargestSide(Shape s) {
        // Put code here
        double LargestSide = 0.0;
        Point prevPt = s.getLastPoint();
        for (Point currPt : s.getPoints()) {
            double currDist = prevPt.distance(currPt);
            if(currDist > LargestSide){
                LargestSide = currDist;
            }
            prevPt = currPt;
        }
        return LargestSide;
    }

    public double getLargestX(Shape s) {
        // Put code here
        double LargestX = 0.0;
        Point prevPt = s.getLastPoint();
        for (Point currPt : s.getPoints()) {
            if(currPt.getX() > LargestX){
                LargestX = currPt.getX();
            }
        }
        return LargestX;
    }

    public double getLargestPerimeterMultipleFiles() {
        // Put code here
        double Largestperimeter = 0.0;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double currentPerimeter = getPerimeter(s);
            if (currentPerimeter > Largestperimeter){
                Largestperimeter = currentPerimeter;
            }

        }
        return Largestperimeter;
    }

    public String getFileWithLargestPerimeter() {
        // Put code here
        double Largestperimeter = 0.0;
        File temp = null;    // replace this code
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double currentPerimeter = getPerimeter(s);
            if (currentPerimeter > Largestperimeter){
                Largestperimeter = currentPerimeter;
                temp = f;
            }

        }
        return temp.getName();
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        System.out.println("perimeter = " + length);
        int number = getNumPoints(s);
        System.out.println("number of points = " + number);
        double average = getAverageLength(s);
        System.out.println("number of average length = " + average);
        double largestlength = getLargestSide(s);
        System.out.println("length of the largest side = " + largestlength);
        double largestX = getLargestX(s);
        System.out.println("largest X = " + largestX);
    }
    
    public void testPerimeterMultipleFiles() {
        // Put code here
        double largestPerimeter = getLargestPerimeterMultipleFiles();
        System.out.println("largest Perimeter: " + largestPerimeter);
    }

    public void testFileWithLargestPerimeter() {
        // Put code here
        String ShapeLargestPerimeter = getFileWithLargestPerimeter();
        System.out.println("shape with the largest Perimeter: " + ShapeLargestPerimeter);
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        pr.testPerimeter();
    }
}
