package id.handiism.tacticalweapon;

public class Weapon {
    private  String name;
    private int firepower;
    private int rateOfFire;
    private int accuracy;
    private int evasion;

    public Weapon(String name, int firepower, int rateOfFire, int accuracy, int evasion) {
        this.name = name;
        this.firepower = firepower;
        this.rateOfFire = rateOfFire;
        this.accuracy = accuracy;
        this.evasion = evasion;
    }

    public final double getDamagePerSecond() {
        return (double) (firepower * rateOfFire) / 60;
    }

    public final int getCombatEffectiveness() {
        return (int) (30 * firepower + 40 * (rateOfFire * rateOfFire / 120) + 15 * (accuracy +evasion));
    }

    public boolean winOver(Weapon weapon) {
        if (this.getAccuracy() * this.getCombatEffectiveness()
                >= weapon.getAccuracy() * weapon.getCombatEffectiveness()) {
            return true;
        } else {
            return false;
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFirepower(int firepower) {
        this.firepower = firepower;
    }

    public void setRateOfFire(int rateOfFire) {
        this.rateOfFire = rateOfFire;
    }

    public void setAccuracy(int accuracy) {
        this.accuracy = accuracy;
    }

    public void setEvasion(int evasion) {
        this.evasion = evasion;
    }

    public String getName() {
        return name;
    }

    public int getFirepower() {
        return firepower;
    }

    public int getRateOfFire() {
        return rateOfFire;
    }

    public int getAccuracy() {
        return accuracy;
    }

    public int getEvasion() {
        return evasion;
    }
}
