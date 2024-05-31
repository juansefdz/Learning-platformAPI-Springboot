package com.simulacro.aprendizaje.infraestructure.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.simulacro.aprendizaje.api.dto.request.MessageRequest;
import com.simulacro.aprendizaje.api.dto.response.MessageResponse.MessageResponse;
import com.simulacro.aprendizaje.domain.entities.Course;
import com.simulacro.aprendizaje.domain.entities.Message;
import com.simulacro.aprendizaje.domain.entities.UserEntity;
import com.simulacro.aprendizaje.domain.repositories.CourseRepository;
import com.simulacro.aprendizaje.domain.repositories.MessageRepository;
import com.simulacro.aprendizaje.domain.repositories.UserRepository;
import com.simulacro.aprendizaje.infraestructure.abstract_services.ImessageService;
import com.simulacro.aprendizaje.utils.enums.SortType;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class MessageService implements ImessageService {

    @Autowired
    private final MessageRepository messageRepository;
    
    @Autowired
    private final UserRepository userRepository; // Para buscar los usuarios

    @Autowired
    private final CourseRepository courseRepository; // Para buscar los cursos

    @Override
    public Page<MessageResponse> getAll(int page, int size, SortType sortType) {
        if (page < 0)
            page = 0;

        PageRequest pagination = PageRequest.of(page, size);
        return this.messageRepository.findAll(pagination).map(this::entityToResponse);
    }

    @Override
    public MessageResponse getById(Long id) {
        return this.entityToResponse(this.find(id));
    }

    private Message find(Long id) {
        return this.messageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Message ID not found with this ID: " + id));
    }

    @Override
    public MessageResponse create(MessageRequest request) {
        Message message = this.requestToEntity(request);
        return this.entityToResponse(this.messageRepository.save(message));
    }

    @Override
    public MessageResponse update(MessageRequest request, Long id) {
        Message message = this.find(id);
        message = this.requestToEntity(request);
        message.setIdMessage(id);
        return this.entityToResponse(this.messageRepository.save(message));
    }

    @Override
    public void delete(Long id) {
        this.messageRepository.delete(this.find(id));
    }

    private Message requestToEntity(MessageRequest request) {
        Message message = new Message();
       
        BeanUtils.copyProperties(request, message);
        

        UserEntity sender = userRepository.findById(request.getSenderId())
                .orElseThrow(() -> new RuntimeException("Sender user not found with ID: " + request.getSenderId()));
        UserEntity receiver = userRepository.findById(request.getReceiverId())
                .orElseThrow(() -> new RuntimeException("Receiver user not found with ID: " + request.getReceiverId()));
        Course course = courseRepository.findById(request.getCourseId())
                .orElseThrow(() -> new RuntimeException("Course not found with ID: " + request.getCourseId()));
        
        message.setSender(sender);
        message.setReceiver(receiver);
        message.setCourse(course);
        
        
        return message;
    }

    private MessageResponse entityToResponse(Message entity) {
        MessageResponse.MessageResponseBuilder responseBuilder = MessageResponse.builder()
                .messageId(entity.getIdMessage())
                .messageContent(entity.getMessageContent())
                .date(entity.getSentDate())
                .senderId(entity.getSender().getIdUser())
                .receiverId(entity.getReceiver().getIdUser());
    
        if (entity.getCourse() != null) {
            responseBuilder.courseId(entity.getCourse().getIdCourse());
        }

        return responseBuilder.build();
    }
    
}
