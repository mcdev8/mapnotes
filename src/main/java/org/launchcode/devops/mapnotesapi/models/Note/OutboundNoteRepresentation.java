package org.launchcode.devops.mapnotesapi.models.Note;

import java.util.ArrayList;
import java.util.List;

public class OutboundNoteRepresentation {

  private long id;
  private String title;
  private String body;

  public static OutboundNoteRepresentation fromNoteEntity(NoteEntity noteEntity) {
    return new OutboundNoteRepresentation(
      noteEntity.getId(),
      noteEntity.getTitle(),
      noteEntity.getBody());
  }

  public OutboundNoteRepresentation(long id, String title, String body){
    this.id = id;
    this.body = body;
    this.title = title;
  }

  public long getId() {
    return this.id;
  }
  public String getTitle(){
    return this.title;
  }
  public String getBody(){
    return this.body;
  }

  public static List<OutboundNoteRepresentation> buildDtosFromNoteEntityList(List<NoteEntity> noteEntities) {
    List<OutboundNoteRepresentation> dtoList = new ArrayList<>();
    for(NoteEntity noteEntity : noteEntities) {
      dtoList.add(new OutboundNoteRepresentation(
        noteEntity.getId(),
        noteEntity.getTitle(),
        noteEntity.getBody())
      );
    }

    return dtoList;
}
}
