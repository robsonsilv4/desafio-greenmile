package com.robson.workedhours.services.commands;

import com.robson.core.domains.WorkedHours;

public interface WorkedHoursCommandService {

  WorkedHours insert(WorkedHours workedHours);
}
