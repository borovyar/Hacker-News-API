package cz.cvut.fit.bioop.hackernewsclient.renderer
import cz.cvut.fit.bioop.hackernewsclient.model.api.{Comment, Story, User}
import cz.cvut.fit.bioop.hackernewsclient.parser.HtmlParser.toFormattedText

import java.io.OutputStream
import java.time.format.DateTimeFormatter
import java.time.{Instant, LocalDateTime, ZoneId}

class RendererImpl extends Renderer{
  override def renderToConsole(message: String): Unit = {
    renderToOutputStream(message, Console.out)
  }

  override def renderToOutputStream[T](entity: T, out: OutputStream): Unit ={
    out.write(entity.toString.getBytes)
    out.flush()
  }

  override def renderStory(story: Story): Unit = {
    val commentsNumber = if(story.comments.isDefined) story.comments.get.size else 0

    val storyString = s"${Console.BOLD}${story.title.getOrElse("-")}${Console.RESET} (${story.url.getOrElse("-")})\n" +
      s"\t${story.score.getOrElse("-")} points by ${story.author.getOrElse("-")} | $commentsNumber comments\n"

    renderToConsole(storyString)
  }

  override def renderComment(comment: Comment): Unit = {
    val date =
      if (comment.created.isDefined)
        LocalDateTime.ofInstant(
          Instant.ofEpochSecond(comment.created.get),
          ZoneId.systemDefault()
        ).format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"))
      else
        "..."

    val commentString = s"${Console.BOLD}${comment.author.getOrElse("...")} at $date${Console.RESET}:" +
      s" ${toFormattedText(comment.text.getOrElse("..."))}\n"

    renderToConsole(commentString)
  }

  override def renderUser(user: User): Unit = {
    val numberOfStories = user.submitted.getOrElse(List()).size
    val aboutUser = user.about.getOrElse("...")
    val registrationDate = LocalDateTime.ofInstant(
      Instant.ofEpochSecond(user.created),
      ZoneId.systemDefault()
    ).format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"))

    val userString = s"The user ${Console.BOLD}${user.id} wrote about himself: ${toFormattedText(aboutUser)}.\n" +
      s"$registrationDate - registration date and karma is ${user.karma}. " +
      s"Number of posted stories - $numberOfStories"

    renderToConsole(userString)
  }
}
