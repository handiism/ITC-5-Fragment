package id.handiism.tacticalweapon;

public class Weapon {
    private  String name;
    private int firepower; // besar kerusakan yang dihasilkan dalam satu serangan
    private int rateOfFire; // banyak serangan yang bisa dilakukan dalam satu menit
    private int accuracy; // kemampuan untuk melakukan serangan dengan akurat
    private int evasion; // kemampuan

    public Weapon(String name, int firepower, int rateOfFire, int accuracy, int evasion) {
        this.name = name;
        this.firepower = firepower;
        this.rateOfFire = rateOfFire;
        this.accuracy = accuracy;
        this.evasion = evasion;
    }

    // getDamagePerSecond untuk mengembalikan nilai damaga per second (bersar kerusakan dalam sedetik)
    public final double getDamagePerSecond() {
        return (double) (firepower * rateOfFire) / 60;
    }

    // getCombatEffectivness mengembalikan nilai keefektifan suatu weapon
    public final int getCombatEffectiveness() {
        return (int) (30 * firepower + 40 * (rateOfFire * rateOfFire / 120) + 15 * (accuracy +evasion));
    }

    // winOver untuk mengetahui apakah suatu weapon bisa menang atau tidak melawan weapon lain
    public boolean winOver(Weapon weapon) {
        if (this.getAccuracy() * this.getCombatEffectiveness()
                >= weapon.getAccuracy() * weapon.getCombatEffectiveness()) {
            return true;
        } else {
            return false;
        }
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
