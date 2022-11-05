package edu.uga.cs.statecapitalquiz;

public class State {
    private long   id;
    private String name;
    private String capital;
    private String city1;
    private String city2;
    private String stateHood;
    private String capitalSince;
    private String capitalRank;

    public State()
    {
        this.id = -1;
        this.name = null;
        this.capital = null;
        this.city1 = null;
        this.city2 = null;
        this.stateHood = null;
        this.capitalSince = null;
        this.capitalRank = null;
    }

    public State(String name, String capital, String city1, String city2, String stateHood, String capitalSince, String capitalRank ) {
        this.id = -1;  // the primary key id will be set by a setter method
        this.name = name;
        this.capital = capital;
        this.city1 = city1;
        this.city2 = city2;
        this.stateHood = stateHood;
        this.capitalSince = capitalSince;
        this.capitalRank = capitalRank;
    }

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public String getStateName()
    {
        return name;
    }

    public void setStateName(String name)
    {
        this.name = name;
    }

    public String getCapital()
    {
        return capital;
    }

    public void setCapital(String capital)
    {
        this.capital = capital;
    }

    public String getCity1()
    {
        return city1;
    }

    public void setCity1(String city1)
    {
        this.city1 = city1;
    }

    public String getCity2()
    {
        return city2;
    }

    public void setCity2(String city2)
    {
        this.city2 = city2;
    }

    public String getStateHood()
    {
        return stateHood;
    }

    public void setStateHood(String stateHood)
    {
        this.stateHood = stateHood;
    }

    public String getCapitalSince()
    {
        return capitalSince;
    }

    public void setCapitalSince(String capitalSince)
    {
        this.capitalSince = capitalSince;
    }

    public String getCapitalRank()
    {
        return capitalRank;
    }

    public void setCapitalRank(String capitalRank)
    {
        this.capitalRank = capitalRank;
    }

    public String toString()
    {
        return id + ": " + name + " " + capital + " " + city1 + " " + city2 + " " + stateHood + " " + capitalSince + " " + capitalRank;
    }
}
