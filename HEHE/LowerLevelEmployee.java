import java.util.List;

public class LowerLevelEmployee extends Employee {
    public LowerLevelEmployee(String id, Demographics demographics, String jobTitle, String department) {
        super(id, demographics, jobTitle, department);
    }
//view sprints evals
    public void viewSprintEvaluations() {
        List<String> evaluations = SprintEval.loadEvaluationsForEmployee(getId());
        if (evaluations.isEmpty()) {
            System.out.println("No evaluations found for employee ID: " + getId());
        } else {
            System.out.println("Sprint Evaluations:");
            for (String eval : evaluations) {
                System.out.println(eval);
            }
        }
    }
}
