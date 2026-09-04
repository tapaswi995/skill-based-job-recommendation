package service;
import model.Job; import java.util.*;
/** Simple explainable rule: matched required skills divided by required skills. */
public class JobRecommendationService {
 public List<Job> rank(List<Job> jobs,List<String> studentSkills){
  Set<String> student=new HashSet<>(); for(String s:studentSkills)student.add(s.toLowerCase());
  for(Job job:jobs){int matched=0;for(String required:job.skills)if(student.contains(required.toLowerCase()))matched++;job.matchPercentage=job.skills.isEmpty()?0:(matched*100/job.skills.size());}
  jobs.sort((a,b)->Integer.compare(b.matchPercentage,a.matchPercentage)); return jobs;
 }
}
