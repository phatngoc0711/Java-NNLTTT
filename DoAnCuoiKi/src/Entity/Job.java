package Entity;

public class Job {
	private String JobID;
	private String JobName;
	private float Value;
	
	public Job() {
		
	}
	public Job(String jobid, String jobname, float value) {
		this.JobID = jobid;
		this.JobName = jobname;
		this.Value = value;
	}
	public String getJobID() {
		return JobID;
	}
	public void setJobID(String jobID) {
		JobID = jobID;
	}
	public String getJobName() {
		return JobName;
	}
	public void setJobName(String jobName) {
		JobName = jobName;
	}
	public float getValue() {
		return Value;
	}
	public void setValue(float value) {
		Value = value;
	}
}
