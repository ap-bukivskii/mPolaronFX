package polaron;

/**
 * Created by tolik_b on 12/18/14.
 */
public class Polaron {
    public      static  final   double  KB      =   8.6173324787879e-5;
    public      static  final   double  sqrtPI  =   Math.sqrt(Math.PI);
    protected                   double  Ep0;
    protected                   double  T0;
    protected                   double  W0;
    protected                   double  Ta;
    protected                   double  lastD;
    protected                   double  lastE;
    protected                   double  lastF;
    protected                   double  lastTE;
    protected                   double  lastTF;
    protected                   double  lastTD;
    protected                   double  lastTDS;
    protected                   double  lastDS;


    Polaron(double W0, double Ta, double Ep0, double T0)    {
        this.Ep0 = Ep0; this.T0 = T0; this.W0 = W0; this.Ta = Ta;}
    Polaron(double W0, double Ta)                           {
        this.W0 = W0; this.Ta = Ta;}

    public      double    getExch     (double T)            {
        if  (T<=0){throw new IllegalArgumentException("Absolute temperature can not be lower or equal zero!");}
        if  (T==this.lastTE){return this.lastE;}
        else{
            this.lastE   =  6.0*(1.0+this.getEp(T)/3.0/Polaron.KB/T)/(1.0+this.getEp(T)/Polaron.KB/T)*this.getEp(T)*ErrorFunction.erf(Math.sqrt(this.getEp(T)/2.0/Polaron.KB/T));
            this.lastTE  =  T;
            return this.lastE;
        }
    }
    public      double    getFluct    (double T)            {
        if  (T<=0){throw new IllegalArgumentException("Absolute temperature can not be lower or equal zero!");}
        if  (T==this.lastTF){return this.lastF;}
        else{
            this.lastF   =   2.0/Polaron.sqrtPI*(1.0+this.getEp(T)/2.0/Polaron.KB/T)/(1+this.getEp(T)/Polaron.KB/T)*(Math.sqrt(8.0*this.getEp(T)*Polaron.KB*T)*Math.exp(-this.getEp(T)/2.0/Polaron.KB/T));
            this.lastTF  =   T;
            return this.lastF;
        }
    }
    public      double    getAvgD     (double T)            {
        if  (T<=0){throw new IllegalArgumentException("Absolute temperature can not be lower or equal zero!");}
        if  (T== this.lastTD){return this.lastD;}
        else {
            this.lastD = this.getFluct(T) + this.getExch(T);
            this.lastTD = T;
            return this.lastD;
        }
    }
    protected   double    getDexp     (double T)            {
        return this.Ep0*Math.exp(-T/T0);}
    public      double    getEp       (double T)            {
        return this.W0*this.W0/4/Polaron.KB/(T+this.Ta);}
    public      double    getAvgDSqr  (double T)            {
        if  (T<=0){throw new IllegalArgumentException("Absolute temperature can not be lower or equal zero!");}
        if  (T== this.lastTDS){return this.lastDS;}
        else {
            this.lastDS = 4 * this.getEp(T) * Polaron.KB * T / (1 + this.getEp(T) / Polaron.KB / T) * (3.0 + 3 * this.getEp(T) / Polaron.KB / T * (6.0 + this.getEp(T) / Polaron.KB / T));
            this.lastTDS = T;
            return this.lastDS;
        }
    }
    public      double    getDiffDs   (double T)            {
        return Math.sqrt( this.getAvgDSqr(T) - this.getAvgD(T)*this.getAvgD(T));}

//    public double       getP    (double D)                  {
//        return 0;
//    }

    //protected double    getW0   (double T)                {
    //    return Math.sqrt(this.getD_2(T)*4*Polaron.KB*T);}
}
