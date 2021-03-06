#ifndef CAMERACALIBRATOR_H
#define CAMERACALIBRATOR_H

#include <opencv2/opencv.hpp>
#include <stdio.h>
#include <stdlib.h>
#include <QString>

using namespace cv;
using namespace std;

class CameraCalibrator
{
public:
    CameraCalibrator();

    int AddChessboardPoints(const vector<Mat> &imageList, Size &boardSize);

    double Calibrate(Size &imageSize);

    Mat Remap(const Mat &image);

    Mat getCameraMatrix() const;

    Mat getDistCoeffs() const;

    QString GetCalibrationAsText();

    void Clear();
private:
    void AddPoints(const vector<Point2f>& imageCorners, const vector<Point3f>& objectCorners);
    void run(); // inherit from QThread

    //input points:
    vector< vector<Point3f> > objectPoints; // real world 3D coordinates
    vector< vector<Point2f> > imagePoints; // 2D coordinates (pixel values)

    //output matrices:
    Mat cameraMatrix;
    Mat distCoeffs;

    int flag; // define how the calibration has to be made

    // used in the undistort process
    Mat map1, map2;
    bool mustInitUndistort;
};

#endif // CAMERACALIBRATOR_H
