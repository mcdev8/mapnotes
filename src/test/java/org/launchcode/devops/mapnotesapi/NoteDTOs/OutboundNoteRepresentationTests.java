package org.launchcode.devops.mapnotesapi.NoteDTOs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.launchcode.devops.mapnotesapi.models.Note.NoteEntity;
import org.launchcode.devops.mapnotesapi.models.Note.OutboundNoteRepresentation;

public class OutboundNoteRepresentationTests {
  @Test
  @DisplayName("[static] fromNoteEntity(): converts a NoteEntity representation to its outbound representation")
  public void fromNoteEntity() {
    NoteEntity testNoteEntity = new NoteEntity("test title", "test body");
    assertTrue(OutboundNoteRepresentation.fromNoteEntity(testNoteEntity)instanceof OutboundNoteRepresentation );
    
    OutboundNoteRepresentation testOutboundNoteRepresentation = OutboundNoteRepresentation.fromNoteEntity(testNoteEntity);
    assertEquals(testOutboundNoteRepresentation.getTitle(), testNoteEntity.getTitle());
    assertEquals(testOutboundNoteRepresentation.getBody(), testNoteEntity.getBody());
  }
}
