using blogApi.Application.dynamic.dots;
using blogApi.Core;
using blogApi.Core.entity;
using blogApi.Core.sqlsugar;

namespace blogApi.Application.dynamic
{
    public class DynamicService : IDynamicApiController
    {
        Repository<Dynamic> _dynamicRepository => new Repository<Dynamic>();

        public ResultVo Query(int categoryId)
        {
            var db = _dynamicRepository.Context;
            List<DynamicView> dynamics = db.Queryable<DynamicView>().Where(it => it.categoryId == categoryId).ToList();
            return ResultVo.success(dynamics);
        }
        public ResultVo Add(Dynamic dynamic)
        {
            dynamic.dynamicId = Tool.GenerateId("dynamic");
            _dynamicRepository.Insert(dynamic);
            return ResultVo.success();
        }
        public ResultVo Delete(string dynamicId)
        {
            _dynamicRepository.DeleteById(dynamicId);
            return ResultVo.success();
        }
    }
}
