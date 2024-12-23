export interface Dynamic {
  dynamicId?: string,
  dynamicContent: string,
  createTime?: string,
  dynamicImages: string[],
  userId: string,
  categoryId: number,
}

export interface DynamicDetail {
  dynamicId: string,
  dynamicContent: string,
  createTime: string,
  dynamicImages: string[],

  userId: string,
  nickname: string,
  avatar: string,
}
