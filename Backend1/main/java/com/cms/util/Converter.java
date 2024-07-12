package com.cms.util;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.cms.entity.Admin;
import com.cms.entity.Cakes;
import com.cms.model.AdminDTO;
import com.cms.model.CakesDTO;

@Component
public class Converter {

	//convert from DTO  to entity
	
	public Cakes covertToInstructorEntity(CakesDTO instructorDTO)
	{
		Cakes instructor=new Cakes();
		if(instructorDTO!=null)
		{
			BeanUtils.copyProperties(instructorDTO, instructor);
		}
		return instructor;
	}
	
	
	//covert from Entity to DTO
	public CakesDTO convertToInstructorDTO(Cakes instructor)
	{
		CakesDTO instructorDTO=new CakesDTO();
		if(instructor!=null)
		{
			BeanUtils.copyProperties(instructor, instructorDTO);
		}
		return instructorDTO;
	}
	
	
	//convert from DTO  to entity
	
		public Admin covertToCourseEntity(AdminDTO courseDTO)
		{
			Admin course=new Admin();
			if(courseDTO!=null)
			{
				BeanUtils.copyProperties(courseDTO, course);
			}
			return course;
		}
		
		//covert from Entity to DTO
			public AdminDTO convertToCourseDTO(Admin course)
			{
				AdminDTO courseDTO=new AdminDTO();
				if(course!=null)
				{
					BeanUtils.copyProperties(course, courseDTO);
				}
				return courseDTO;
			}
}
