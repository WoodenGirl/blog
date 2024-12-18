import { format } from 'date-fns'

const formatDate = (date: string) => {
  // 使用date-fns格式化日期
  return format(date, 'yyyy-MM-dd HH:mm:ss');
}

export { formatDate}
