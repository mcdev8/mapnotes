package org.launchcode.devops.mapnotesapi.NoteDTOs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.launchcode.devops.mapnotesapi.models.Note.InboundNoteRepresentation;
import org.launchcode.devops.mapnotesapi.models.Note.NoteEntity;
public class InboundNoteRepresentationTests {
  @Test
  @DisplayName("[instance] toNote(): converts the inbound representation to a NoteEntity representation")
  public void toNoteEntity() {
    InboundNoteRepresentation inboundNote = new InboundNoteRepresentation("test title", "test body");
    assertTrue(inboundNote.toNoteEntity() instanceof NoteEntity );

    NoteEntity testONoteEntity = inboundNote.toNoteEntity();
    assertEquals(testONoteEntity.getTitle(), inboundNote.getTitle());
    assertEquals(testONoteEntity.getBody(), inboundNote.getBody());
  }
}
