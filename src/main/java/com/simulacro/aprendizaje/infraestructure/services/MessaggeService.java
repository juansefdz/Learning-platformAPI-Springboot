package com.simulacro.aprendizaje.infraestructure.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.simulacro.aprendizaje.api.dto.request.MessaggeRequest;
import com.simulacro.aprendizaje.api.dto.request.UserRequest;
import com.simulacro.aprendizaje.api.dto.response.MessaggeResponse;
import com.simulacro.aprendizaje.api.dto.response.UserResponse;
import com.simulacro.aprendizaje.domain.entities.Messagge;
import com.simulacro.aprendizaje.domain.entities.UserEntity;
import com.simulacro.aprendizaje.domain.repositories.MessaggeRepository;
import com.simulacro.aprendizaje.infraestructure.abstract_services.ImessaggeService;
import com.simulacro.aprendizaje.utils.enums.SortType;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class MessaggeService implements ImessaggeService {

    @Autowired
    private final MessaggeRepository messaggeRepository;

    @Override
    public Page<MessaggeResponse> getAll(int page, int size, SortType sortType) {
        if (page < 0)
            page = 0;

        PageRequest pagination = PageRequest.of(page, size);
        return this.messaggeRepository.findAll(pagination).map(this::entityToResponse);
    }

     @Override
    public MessaggeResponse getById(Long id) {
        return this.entityToResponse(this.find(id));
    }
    private Messagge find(Long id) {
        return this.messaggeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("messagge ID not found with this ID: " + id));
    }


    @Override
    public MessaggeResponse create(MessaggeRequest request) {
        Messagge messagge = this.requestToEntity(request);
        return this.entityToResponse(this.messaggeRepository.save(messagge));
    }

    @Override
    public MessaggeResponse update(MessaggeRequest request, Long id) {
        Messagge messagge = this.find(id);
        messagge = this.requestToEntity(request);
        messagge.setIdMessage(id);
        return this.entityToResponse(this.messaggeRepository.save(messagge));
    }

    @Override
    public void delete(Long id) {
        this.messaggeRepository.delete(this.find(id));
    }


    private Messagge requestToEntity(MessaggeRequest request) {
        Messagge messagge = new Messagge();
        BeanUtils.copyProperties(request, messagge);
        return messagge;
    }

    private MessaggeResponse entityToResponse(Messagge entity) {
        return MessaggeResponse.builder()
                .messageId(entity.getIdMessage())
                .messageContent(entity.getMessageContent())
                .date(entity.getSentDate())
                .senderId(entity.getSender().getIdUser())
                .receiverId(entity.getReceiver().getIdUser())
                .courseId(entity.getCourse().getIdCourse())
                .build();
    }

}
