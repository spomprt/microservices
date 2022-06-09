package com.spomprt.orchestrator.aggregate;

import com.fasterxml.jackson.databind.JsonNode;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.util.UUID;

@Builder
@Getter
@Setter()
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "command")
@TypeDefs({
        @TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
})
public class Command {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    private String aggregatetype;

    private String aggregateid;

    private String type;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private JsonNode payload;

}
