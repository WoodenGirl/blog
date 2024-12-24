export interface Comment {
  commentId?: number,
  commentParent: number,
  userId: string,
  linkId: string,
  commentReply?: number,
  commentContent: string,
  createdTime?: string,
}

export interface CommentDetail {
  commentId: number,
  commentParent: number,
  linkId: string,
  commentContent: string,
  createdTime: string,
  replyName: string,

  userId: string,
  nickname: string,
  avatar: string,

  commentChildren: Comment[],
}

