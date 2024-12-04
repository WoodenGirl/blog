import { format } from 'date-fns'
import type { ImageElement } from '../../../env'

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

// 生成13位的uid
const generateUID = (): string => {
  const timestamp = Date.now().toString(36); // 使用36进制，可以包含字母
  const random = Math.random().toString(36).substr(2, 9); // 获取随机数的一部分，并转换为36进制
  return timestamp + random;
}

// list1包含list2,return list1-list2
function findDifferentAttributes(list1: ImageElement[], list2: ImageElement[]): string[] {
  const differences: string[] = [];
  list1.forEach((item1, index) => {
    const item2 = list2.find(({ src }) => src === item1.src);
    if (!item2) {
      differences.push(item1.src.replace("http://obs.aprilsxz.fun/", ''));
    }
  });
  return differences;
}

export { formatDate, getNow, generateUID, findDifferentAttributes }
