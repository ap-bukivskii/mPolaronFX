package polaron;

import javafx.application.Platform;

import java.io.File;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by tolik_b on 1/5/15.
 */
public class Worker {
    double W0;
    double Ta;
    double maxTemp = 300;
    double tempStep = 0.1;
    boolean[] format;

    public Worker(boolean[] format, double W0, double Ta )    {
        this.format     =   format;
        if (format.length!=8){Platform.exit();} // КОСТИЛІ !!!!!!
        this.W0 = W0;
        this.Ta = Ta;

    }
    public void run()   {
        Polaron p = new Polaron(W0,Ta);
        String path = "polaron/W0_" + W0*1000 + "_meV___Ta_" + Ta + "_K.txt";
        File dir = new File ("polaron");
        if(!dir.isDirectory()){dir.mkdir();} // КОСТИЛІ !!!!!!
        PrintWriter writer = null;
        try {writer = new PrintWriter(path, "UTF-8");} catch (Exception e) {e.printStackTrace(); System.exit(0);}

        if (format[0]) {writer.print("T\t");}
        if (format[1]) {writer.print("Ep\t");}
        if (format[2]) {writer.print("<\\g(D)>_exch/2\t");}
        if (format[3]) {writer.print("<\\g(D)>_fluct/2\t");}
        if (format[4]) {writer.print("<\\g(D)>/2\t");}
        if (format[5]) {writer.print("<\\g(D)>^2\t");}
        if (format[6]) {writer.print("<\\g(D)^2>\t");}
        if (format[7]) {writer.print("sqrt(<\\g(D)^2>-<\\g(D)>^2)\t");}
        writer.println("");
        if (format[0]) {writer.print("K\t");}
        if (format[1]) {writer.print("meV\t");}
        if (format[2]) {writer.print("meV\t");}
        if (format[3]) {writer.print("meV\t");}
        if (format[4]) {writer.print("meV\t");}
        if (format[5]) {writer.print("meV^2\t");}
        if (format[6]) {writer.print("meV^2\t");}
        if (format[7]) {writer.print("meV\t");}

        for (double t=0.1;t<this.maxTemp;t+=tempStep){
            writer.println("");
            if (format[0]) {writer.print(t+"\t");}
            if (format[1]) {writer.print(1000*p.getEp(t)+"\t");}
            if (format[2]) {writer.print(1000*p.getExch(t)/2+"\t");}
            if (format[3]) {writer.print(1000*p.getFluct(t)/2+"\t");}
            if (format[4]) {writer.print(1000*p.getAvgD(t)/2+"\t");}
            if (format[5]) {writer.print(1000*1000*p.getAvgD(t)*p.getAvgD(t)+"\t");}
            if (format[6]) {writer.print(1000*1000*p.getAvgDSqr(t)+"\t");}
            if (format[7]) {writer.print(1000*p.getDiffDs(t));}
        }
        writer.close();
    }
}
