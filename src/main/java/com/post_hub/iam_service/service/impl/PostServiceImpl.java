package com.post_hub.iam_service.service.impl;

import com.post_hub.iam_service.exception.DataExistException;
import com.post_hub.iam_service.exception.NotFoundException;
import com.post_hub.iam_service.mapper.PostMapper;
import com.post_hub.iam_service.model.constants.ApiErrorMessage;
import com.post_hub.iam_service.model.dto.post.PostDTO;
import com.post_hub.iam_service.model.entity.Post;
import com.post_hub.iam_service.model.request.post.PostRequest;
import com.post_hub.iam_service.model.response.IamResponse;
import com.post_hub.iam_service.repository.PostRepository;
import com.post_hub.iam_service.service.PostService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final PostMapper postMapper;

    @Override
    public IamResponse<PostDTO> getById(@NotNull Integer postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new NotFoundException(ApiErrorMessage.POST_NOT_FOUND_BY_ID.getMessage(postId)));

        PostDTO postDTO = postMapper.toPostDto(post);
        return IamResponse.createSuccessful(postDTO);
    }

    @Override
    public IamResponse<PostDTO> createPost(@NotNull PostRequest postRequest) {
        if(postRepository.existsByTitle(postRequest.getTitle())) {
            throw new DataExistException(ApiErrorMessage.POST_ALREADY_EXISTS.getMessage(postRequest.getTitle()));
        }
        Post post = postMapper.createPost(postRequest);
        Post savePost = postRepository.save(post);
        PostDTO postDTO = postMapper.toPostDto(savePost);

        return IamResponse.createSuccessful(postDTO);
    }
}
