package com.edu.zzti.ass.management.service;

import com.edu.zzti.ass.management.model.TClass;
import com.edu.zzti.ass.management.model.TStudent;
import com.edu.zzti.ass.management.model.TTeacher;

public interface IInfoWriteService {

	public   void saveInfo(TClass tClass, TStudent student, TTeacher teacher);

}
