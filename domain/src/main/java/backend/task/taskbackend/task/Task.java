package backend.task.taskbackend.task;

import backend.task.taskbackend.customer.dto.SimpleCustomer;

import java.util.Date;

class Task {
    static Task restore(TaskSnapshot taskSnapshot){
        return new Task(taskSnapshot.getId(),
                taskSnapshot.getTitle(),
                taskSnapshot.getImportance(),
                taskSnapshot.getDescription(),
                taskSnapshot.getCreationDate(),
                taskSnapshot.getDeadLine(),
                SimpleCustomer.restore(taskSnapshot.getCustomer()));
    }
    private final int id;
    private final String title;
    private final String importance;
    private final String description;
    private final Date creationDate;
    private final Date deadLine;
    private final SimpleCustomer customer;

    private Task(int id, String title, String importance, String description, Date creationDate, Date deadLine, SimpleCustomer customer) {
        this.id = id;
        this.title = title;
        this.importance = importance;
        this.description = description;
        this.creationDate = creationDate;
        this.deadLine = deadLine;
        this.customer = customer;
    }
    TaskSnapshot getSnapshot(){
        return TaskSnapshot.builder()
                .id(id)
                .title(title)
                .importance(importance)
                .description(description)
                .creationDate(creationDate)
                .deadLine(deadLine)
                .customer(customer.getSnapshot())
                .build();
    }
}
