using blogApi.Application.comment.dots;
using blogApi.Core.entity;
using blogApi.Core.sqlsugar;

namespace blogApi.Application.comment
{
    public class CommentService: IDynamicApiController
    {
        Repository<Comment> _commentRepository => new Repository<Comment>();

        public ResultVo Query(string linkedId)
        {
            var db = _commentRepository.Context;
            List<CommentView> comments = db.Queryable<CommentView>().Where(it => it.linkedId == linkedId).ToList();
            return ResultVo.success(comments);
        }
        [HttpGet]
        public ResultVo Count(string linkedId)
        {
            var db = _commentRepository.Context;
            int count = db.Queryable<CommentView>().Where(it => it.linkedId == linkedId).Count();
            return ResultVo.success(count);
        }
        public ResultVo Add(Comment comment)
        {
            _commentRepository.Insert(comment);
            return ResultVo.success();
        }
        public ResultVo Delete(int commentId)
        {
            _commentRepository.DeleteById(commentId);
            return ResultVo.success();
        }
        public ResultVo DeleteCascade(string linkedId)
        {
            _commentRepository.Delete(it => it.linkedId == linkedId);
            return ResultVo.success();
        }
    }
}
