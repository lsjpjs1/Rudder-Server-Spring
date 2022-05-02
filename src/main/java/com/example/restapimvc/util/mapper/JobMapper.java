package com.example.restapimvc.util.mapper;

import com.example.restapimvc.job.command.domain.JobFavorite;
import com.example.restapimvc.job.command.dto.JobDto;
import com.example.restapimvc.post.command.domain.Post;
import com.example.restapimvc.post.command.dto.CommonPostDto;
import com.example.restapimvc.post.command.dto.PostMetaDataDto;
import com.example.restapimvc.post.command.dto.WritePostDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
@Mapper
public interface JobMapper {
    @Mapping(target="jobId", source="job.jobId")
    JobDto.FavoriteJobResponse entityToFavoriteJobResponse(JobFavorite jobFavorite);
}
