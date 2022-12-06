package com.cg.spc.services;

import java.util.List;
import com.cg.spc.entities.Standard;

public interface IStandardService {
	public Standard addDetails(Standard standard);
	public Standard getDetailsById(int id);
	public Standard updateDetails(Standard standard,List<Integer> studentIdList);
	public Standard deleteDetailsById(int id);
}
