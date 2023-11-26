package com.ricardo.web.dto;

import com.ricardo.web.model.Requirement;
import lombok.Data;

@Data
public class RequirementDO {
    private String salaryCircle;
    private String workType;
    private String workSchedule;
    private String industry;
    private int minSalary;
    private int maxSalary;

    public Requirement toRequirement(){
        Requirement requirement = new Requirement();
        requirement.setIndustry(this.industry);
        requirement.setSalaryCircle(this.salaryCircle);
        requirement.setWorkType(this.workType);
        requirement.setWorkSchedule(this.workSchedule);
        int[] ints = {minSalary, maxSalary};
        requirement.setSalaryRange(ints);
        return requirement;
    }
}
