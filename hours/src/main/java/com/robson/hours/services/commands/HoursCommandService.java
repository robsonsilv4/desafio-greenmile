package com.robson.hours.services.commands;

import com.robson.core.domains.WorkedHours;

public interface HoursCommandService {

  WorkedHours insert(WorkedHours workedHours);
}
