//store (x,y)
class Pair
{
    public int x, y;

    Pair()
    {
        this(0, 0);
    }

    Pair(int a, int b)
    {
        x = a;
        y = b;
    }

    public boolean equals(Pair a)
    {
        return equals(a.x, a.y);
    }

    public boolean equals(int a, int b)
    {
        return x == a && y == b;
    }

    public String toString()
    {
        return "x: " + x + " y: " + y;
    }
}
