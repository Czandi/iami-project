package com.app.iami.controller.mapper;

import com.app.iami.controller.dto.PresenceDto;
import com.app.iami.model.Presence;

import java.util.List;
import java.util.stream.Collectors;

public class PresenceMapper {

    static public List<PresenceDto> mapToPresenceDtos(List<Presence> presences){
        return presences.stream().map(presence -> mapToPresenceDto(presence)).collect(Collectors.toList());
    }

    private static PresenceDto mapToPresenceDto(Presence presence) {
        return PresenceDto.builder()
                    .id(presence.getId())
                    .date(presence.getDate())
                    .presence(presence.isPresence())
                    .build();
    }

//    static public List<PresenceDto> mapToPresenceDtos(List<Presence> presences){
//
//        LocalDate prevDate = presences.get(0).getDate();
//        LocalDate date = presences.get(0).getDate();
//        List<PresenceResponse> presencesList = new ArrayList<>();
//        List<PresenceDto> presenceDtos = new ArrayList<>();
//
//        for(int i = 0; i < presences.size(); i++){
//
//            Presence presence = presences.get(i);
//            date = presence.getDate();
//
//            PresenceResponse presenceResponse = PresenceResponse.builder()
//                    .id(presence.getId())
//                    .presence(presence.isPresence())
//                    .student(presence.getStudent())
//                    .build();
//
//            if(!date.isEqual(prevDate)){
//                PresenceDto newPresenceDto = PresenceDto.builder()
//                        .date(prevDate)
//                        .presences(presencesList)
//                        .build();
//
//                presenceDtos.add(newPresenceDto);
//
//                presencesList = new ArrayList<>();
//            }
//
//            presencesList.add(presenceResponse);
//
//            prevDate = date;
//        }
//
//        PresenceDto newPresenceDto = PresenceDto.builder()
//                .date(date)
//                .presences(presencesList)
//                .build();
//
//        presenceDtos.add(newPresenceDto);
//
//        return presenceDtos;
//    }

}
