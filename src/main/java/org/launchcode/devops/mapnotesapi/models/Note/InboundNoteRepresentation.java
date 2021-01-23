package org.launchcode.devops.mapnotesapi.models.Note;

public class InboundNoteRepresentation {
  private String title;
  private String body;

  public NoteEntity toNoteEntity() {
    return new NoteEntity(this.title, this.body);
  }
  public InboundNoteRepresentation(String title, String body){
    this.title = title;
    this.body = body;
  }

  public String getTitle(){
    return this.title;
  }
  public String getBody(){
    return this.body;
  }

}
