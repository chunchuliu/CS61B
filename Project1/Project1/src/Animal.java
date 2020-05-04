public class Animal {
    private int kindOfAnimal;
    private int lifeTime;

    Animal() {
        kindOfAnimal = 0;
        lifeTime = 0;
    }
    Animal(int k, int l) {
        kindOfAnimal = k;
        lifeTime = l;
    }
    public void setKind(int kind) {
        kindOfAnimal = kind;
    }
    public void setLifeTime(int time) {
        lifeTime = time;
    }
    public int getKind() {
        return kindOfAnimal;
    }
    public int getLifeTime() {
        return lifeTime;
    }

}
