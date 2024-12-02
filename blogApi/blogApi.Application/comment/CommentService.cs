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
            string viewSql = "SELECT * FROM view_comment where linkedId = \"" + linkedId + "\"";
            List<CommentView> comments = db.Ado.SqlQuery<CommentView>(viewSql);
            return ResultVo.success(comments);
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
    }
}
