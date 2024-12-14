export interface Dynamic {
  dynamicId?: number,
  dynamicContent: string,
  createdTime?: string,
  dynamicImages: string,
  userId: string,
  categoryId: number,
}

export interface DynamicDetail {
  dynamicId: number,
  dynamicContent: string,
  createdTime: string,
  dynamicImages: string,

  userId: string,
  nickname: string,
  avatar: string,

  categoryName: string,
}