//@author A0113672L
package list.model;

import java.util.Collections;
import java.util.List;

import list.CommandBuilder.RepeatFrequency;

public class Task implements ITask {
    private String title;
	private Date startDate;
	private Date endDate;
	private RepeatFrequency repeatFrequency;
	private String place;
	private ICategory category;
	private String notes;
	private TaskStatus status;
	private List<ITask> list;
	
	public Task() {
	    title = "";
	    startDate = Date.getFloatingDate();
	    endDate = Date.getFloatingDate();
	    repeatFrequency = RepeatFrequency.NONE;
	    place = "";
	    category = Category.getDefaultCategory();
	    notes = "";
	    status = TaskStatus.PENDING;
	}
	
	@Override
	public int compareTo(ITask o) {
		if (this.getTimelineDate().compareTo(o.getTimelineDate()) == 0) {
			return this.getTitle().compareToIgnoreCase(o.getTitle());
		} else {
			return this.getTimelineDate().compareTo(o.getTimelineDate());
		}
	}

	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public Task setTitle(String title) {
		if (title != null) {
		    this.title = title;
		}
		return this;
	}

	@Override
	public Date getStartDate() {
		return startDate;
	}

	@Override
	public Task setStartDate(Date startDate) {
	    if (startDate != null) {
	        this.startDate = startDate;
	    }
		return this;
	}

	@Override
	public Date getEndDate() {
		return endDate;
	}

	@Override
	public Task setEndDate(Date endDate) {
	    if (endDate != null) {
	        this.endDate = endDate;
	    }
		return this;
	}

	/**
	 * Returns the Date to be displayed in the user interface
	 * 
	 * floating: --> floatingDate
	 * overdue: if endDate < today --> endDate
	 * current: if endDate >= today --> today
	 * 			if startDate > today --> startDate
	 * 
	 */
	@Override
	public Date getTimelineDate() {
		Date today = new Date();
		
		if (this.hasDeadline()) {
			if (this.isOverdue()) {
				return this.endDate;
			} else {
				if (this.startDate.equals(Date.getFloatingDate())) {
					return this.endDate;
				} else if (today.compareTo(this.startDate) > 0) {
					return today;
				} else {
					return this.startDate;
				}
			}
		} else {
			return Date.getFloatingDate();
		}
	}
	
	@Override
	public RepeatFrequency getRepeatFrequency() {
		return repeatFrequency;
	}

	@Override
	public Task setRepeatFrequency(RepeatFrequency repeatFrequency) {
	    if (repeatFrequency != null) {
	        this.repeatFrequency = repeatFrequency;
	    }
		return this;
	}

	@Override
	public String getPlace() {
		return place;
	}

	@Override
	public Task setPlace(String place) {
	    if (place != null) {
	        this.place = place;
	    }
		return this;
	}

	@Override
	public ICategory getCategory() {
		return category;
	}

	@Override
	public Task setCategory(ICategory category) {
	    if (category != null) {
	    	this.category.getList().remove(this);
	        category.getList().add(this);
	        Collections.sort(category.getList());
	    	this.category = category;
	    }
	    
		return this;
	}
	
	@Override
	public String getNotes() {
		return notes;
	}

	@Override
	public Task setNotes(String notes) {
	    if (notes != null) {
	        this.notes = notes;
	    }
		return this;
	}

	@Override
	public TaskStatus getStatus() {
		return status;
	}

	@Override
	public void setStatus(TaskStatus status) {
	    if (status != null) {
	        this.status = status;
	    }
	}

	@Override
	public List<ITask> getList() {
		return this.list;
	}
	
	@Override
	public void setList(List<ITask> list) {
		this.list = list;
	}
	
	@Override
	public boolean hasDeadline() {
		return !this.getEndDate().equals(Date.getFloatingDate());
	}

	@Override
	public boolean isOverdue() {
		Date today = new Date();
    	return (this.getEndDate().compareTo(today) < 0);    
	}
	
}
