export class Link {
  constructor(public linkId: number,
              public originalLink: string,
              public shortLink: string,
              public clicksCount: number,
              public tags: string,
              public summary: string,
              public creationDate: Date,
              public idEditing=false) {
  }
}
