// 生成13位的uid
export const generateUID = (): string => {
  const timestamp = Date.now().toString(36); // 使用36进制，可以包含字母
  const random = Math.random().toString(36).substr(2, 9); // 获取随机数的一部分，并转换为36进制
  return timestamp + random;
}
