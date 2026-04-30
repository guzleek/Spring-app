package com.post_hub.iam_service.mapper;

import com.post_hub.iam_service.model.dto.post.PostDTO;
import com.post_hub.iam_service.model.dto.post.PostSearchDTO;
import com.post_hub.iam_service.model.entity.Post;
import com.post_hub.iam_service.model.request.post.NewPostRequest;
import com.post_hub.iam_service.model.request.post.UpdatePostRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface PostMapper {

    PostDTO toPostDTO(Post post);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "created", ignore = true)
    Post createPost(NewPostRequest newPostRequest);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "created", ignore = true)
    Post updatePost(@MappingTarget Post post, UpdatePostRequest request);


    PostSearchDTO toPostSearchDTO(Post post);
}
