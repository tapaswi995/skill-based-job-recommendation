package model;

import java.util.*;
public class Job {
    public int id; public String company, title, location, description, eligibility, postedDate;
    public List<String> skills = new ArrayList<>(); public int matchPercentage;
}
