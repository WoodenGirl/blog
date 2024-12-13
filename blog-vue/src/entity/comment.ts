export interface Comment {
  commentId: number,
  commentParent: number,
  userId: string,
  linkedId: string,
  commentReply: number,
  commentContent: string,
  createdTime: string,
  replyName: string,
}

export interface CommentDetail {
  commentId: number,
  commentParent: number,
  linkedId: string,
  commentReply: number,
  commentContent: string,
  createdTime: string,
  replyName: string,

  userId: string,
  nickname: string,
  avatar: string,

  children: Comment[],
}

