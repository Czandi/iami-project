package com.app.iami.controller.mapper;

import com.app.iami.controller.dto.PresenceDto;
import com.app.iami.model.Presence;

import java.util.List;
import java.util.stream.Collectors;

public class PresenceMapper {

    static public List<PresenceDto> mapToPresenceDtos(List<Presence> presences){
        return presences.stream().map(presence -> mapToPresenceDto(presence)).collect(Collectors.toList());
    }

    static public PresenceDto mapToPresenceDto(Presence presence) {
        return PresenceDto.builder()
                .id(presence.getId())
                .date(presence.getDate())
                .presence(presence.isPresence())
                .student(presence.getStudent())
                .build();
    }

}
