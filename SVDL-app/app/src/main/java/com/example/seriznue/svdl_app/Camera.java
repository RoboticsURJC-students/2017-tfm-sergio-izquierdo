package com.example.seriznue.svdl_app;

/**
 * Created by seriznue on 10/22/17.
 */

import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfDouble;

import java.util.ArrayList;

public class Camera {
    private double xo = -10.0, yo = 0.0, zo = -20.0;

    private double omega = 0.0, phi = 0.0, kappa = 0.0;

    private double fx = 336.357, fy = 336.134, cx = 309.413, cy = 246.087;

    private double k1 = 0.0228239, k2 = -0.039873, p1 = 0.000216554, p2 = 0.000243477, k3 = 0.0226664;

    private static Camera instance = null;

    protected Camera() {

    }

    public static Camera getInstance() {
        if (instance == null) {
            instance = new Camera();
        }

        return instance;
    }

    public void changePosition() {
        omega += 0.01;
        phi += 0.01;
        kappa += 0.01;
    }

    public double getXo() {
        return xo;
    }

    public double getYo() {
        return yo;
    }

    public double getZo() {
        return zo;
    }

    public double getOmega() {
        return omega;
    }

    public double getPhi() {
        return phi;
    }

    public double getKappa() {
        return kappa;
    }

    public Mat getRVec() {

        Mat rvec = new Mat(3, 1, CvType.CV_32F);
        rvec.put(0, 0, omega);
        rvec.put(1, 0, phi);
        rvec.put(2, 0, kappa);

        return rvec;
    }

    public Mat getTVec() {

        Mat tvec = new Mat(3, 1, CvType.CV_32F);

        tvec.put(0, 0, xo);
        tvec.put(1, 0, yo);
        tvec.put(2, 0, zo);

        return tvec;
    }

    public Mat getCameraMatrix() {

        Mat cameraMatrix = new Mat(3, 3, CvType.CV_32F);

        cameraMatrix.put(0, 0, fx);
        cameraMatrix.put(0, 1, 0);
        cameraMatrix.put(0, 2, cx);

        cameraMatrix.put(1, 0, 0);
        cameraMatrix.put(1, 1, fy);
        cameraMatrix.put(1, 2, cy);

        cameraMatrix.put(2, 0, 0);
        cameraMatrix.put(2, 1, 0);
        cameraMatrix.put(2, 2, 1);

        return cameraMatrix;
    }

    public MatOfDouble getDistortionModel() {

        ArrayList ld = new ArrayList<>(5);
        ld.add(p1);
        ld.add(p2);
        ld.add(k1);
        ld.add(k1);
        ld.add(k2);

        MatOfDouble distModel = new MatOfDouble();
        distModel.fromList(ld);


        return distModel;
    }

    public Mat getDistortionMat() {

        Mat distortionMat = new Mat(1, 5, CvType.CV_32F);

        distortionMat.put(0, 0, p1);
        distortionMat.put(0, 1, p2);
        distortionMat.put(0, 2, k1);
        distortionMat.put(0, 3, k2);
        distortionMat.put(0, 4, k3);

        return distortionMat;
    }
}
