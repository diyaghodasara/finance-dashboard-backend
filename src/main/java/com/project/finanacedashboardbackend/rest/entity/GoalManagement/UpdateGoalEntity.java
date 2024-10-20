package com.project.finanacedashboardbackend.rest.entity.GoalManagement;

import com.project.finanacedashboardbackend.rest.constants.CustomAnnotation.ValidDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateGoalEntity {
    @NotNull(message = "goal-id cannot be null")
    @Min(value = 1,message = "goal-id should be greater than 0")
    private Integer goalId;

    @NotNull(message = "user-id cannot be null")
    @Min(value = 1,message = "user-id should be greater than 0")
    private Integer userId;

    @NotBlank(message = "goal-name cannot to be blank or null")
    private String goalName;

    @NotNull(message = "target amount cannot be null")
    @DecimalMin(value = "0.01", message = "target amount should be should be greater than 0.0")
    private BigDecimal goalTargetAmount;

    @NotNull(message = "current progress cannot be null")
    private BigDecimal goalCurrentProgress;

    @NotNull(message = "start-date cannot be null")
    @ValidDate(message = "Invalid start date format. Expected format: yyyy-MM-dd")
    private Date goalStartDate;

    @NotNull(message = "end-date cannot be null")
    @ValidDate(message = "Invalid end date format. Expected format: yyyy-MM-dd")
    private Date goalEndDate;

    @NotBlank(message = "status cannot be null or blank")
    private String goalStatus;

    @NotNull(message = "goal priority cannot be null")
    @Min(value = 1,message = "goal priority should be greater than 0")
    private Integer goalPriority;

    @NotBlank(message = "description cannot be null or blank")
    private String goalDescription;
}
