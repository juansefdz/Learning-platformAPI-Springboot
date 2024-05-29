package com.simulacro.aprendizaje.infraestructure.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.simulacro.aprendizaje.api.dto.request.MessageRequest;
import com.simulacro.aprendizaje.api.dto.response.MessageResponse;
import com.simulacro.aprendizaje.domain.entities.Message;
import com.simulacro.aprendizaje.domain.repositories.MessageRepository;
import com.simulacro.aprendizaje.infraestructure.abstract_services.ImessageService;
import com.simulacro.aprendizaje.utils.enums.SortType;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class MessageService implements ImessageService {

    @Autowired
    private final MessageRepository messaggeRepository;

    @Override
    public Page<MessageResponse> getAll(int page, int size, SortType sortType) {
        if (page < 0)
            page = 0;

        PageRequest pagination = PageRequest.of(page, size);
        return this.messaggeRepository.findAll(pagination).map(this::entityToResponse);
    }

    @Override
    public MessageResponse getById(Long id) {
        return this.entityToResponse(this.find(id));
    }

    private Message find(Long id) {
        return this.messaggeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("messagge ID not found with this ID: " + id));
    }

    @Override
    public MessageResponse create(MessageRequest request) {
        Message messagge = this.requestToEntity(request);
        return this.entityToResponse(this.messaggeRepository.save(messagge));
    }

    @Override
    public MessageResponse update(MessageRequest request, Long id) {
        Message messagge = this.find(id);
        messagge = this.requestToEntity(request);
        messagge.setIdMessage(id);
        return this.entityToResponse(this.messaggeRepository.save(messagge));
    }

    @Override
    public void delete(Long id) {
        this.messaggeRepository.delete(this.find(id));
    }

    private Message requestToEntity(MessageRequest request) {
        Message messagge = new Message();
        BeanUtils.copyProperties(request, messagge);
        return messagge;
    }

    private MessageResponse entityToResponse(Message entity) {
        return MessageResponse.builder()
                .messageId(entity.getIdMessage())
                .messageContent(entity.getMessageContent())
                .date(entity.getSentDate())
                .senderId(entity.getSender().getIdUser())
                .receiverId(entity.getReceiver().getIdUser())
                .courseId(entity.getCourse().getIdCourse())
                .build();
    }

}
