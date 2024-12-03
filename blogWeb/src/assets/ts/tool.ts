import { format } from 'date-fns'

const formatDate = (date: string) => {
  // 使用date-fns格式化日期
  return format(date, 'yyyy-MM-dd HH:mm:ss');
}

const getNow = () => {
  return new Date(Date.now() + 8 * 3600 * 1000).toISOString()
};


const generateRandomFileName = () => {
  const timestamp = new Date().getTime();
  const randomNum = Math.floor(Math.random() * 1000000);
  return `${timestamp}_${randomNum}`;
}

export { formatDate, getNow, generateRandomFileName }
