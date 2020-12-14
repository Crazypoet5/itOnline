package com.rjw.itonline.edu.service.impl;

import com.rjw.itonline.edu.entity.Comment;
import com.rjw.itonline.edu.mapper.CommentMapper;
import com.rjw.itonline.edu.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 评论 服务实现类
 * </p>
 * @author 饶嘉伟
 * @since 2020-12-08
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

}
