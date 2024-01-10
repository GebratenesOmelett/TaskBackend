package backend.task.taskbackend.task;

import java.util.Date;

class Task {
    static Task restore(TaskSnapshot taskSnapshot){
        return new Task(taskSnapshot.getId(),
                taskSnapshot.getTitle(),
                taskSnapshot.getImportance(),
                taskSnapshot.getDescription(),
                taskSnapshot.getCreationDate(),
                taskSnapshot.getDeadLine());
    }
    private final int id;
    private final String title;
    private final String importance;
    private final String description;
    private final Date creationDate;
    private final Date deadLine;

    private Task(int id, String title, String importance, String description, Date creationDate, Date deadLine) {
        this.id = id;
        this.title = title;
        this.importance = importance;
        this.description = description;
        this.creationDate = creationDate;
        this.deadLine = deadLine;
    }
    TaskSnapshot getSnapshot(){
        return TaskSnapshot.builder()
                .id(id)
                .title(title)
                .importance(importance)
                .description(description)
                .creationDate(creationDate)
                .deadLine(deadLine)
                .build();
    }
}
