package org.talangsoft.rest.devtools.fullapptest.bookinventory.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.talangsoft.rest.devtools.logging.Loggable;
import org.talangsoft.rest.devtools.fullapptest.bookinventory.api.BookDTO;
import org.talangsoft.rest.devtools.fullapptest.bookinventory.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import javax.validation.Valid;
import java.net.URI;
import java.net.URLDecoder;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/books")
public class BookResource implements Loggable {

    public static final String ENCODING_UTF_8 = "UTF-8";
    @Autowired
    BookService bookService;

    @RequestMapping(value = "/{isbn}",
            method = RequestMethod.GET,
            produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public BookDTO getByIsbn(@PathVariable String isbn) {
        logger().info("Find book by isbn '{}'",isbn);
        return bookService.findByIsbn(isbn);
    }

    @RequestMapping(
            params = {"author"},
            method = RequestMethod.GET,
            produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<BookDTO> getByAuthor(@RequestParam(value = "author",required = true) String author) throws Exception{
        String decodedSearchString = URLDecoder.decode(author, ENCODING_UTF_8);
        logger().info("Get book by author '{}'",decodedSearchString);
        return bookService.findByAuthor(decodedSearchString);
    }


    @RequestMapping(
            params = {"title"},
            method = RequestMethod.GET,
            produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<BookDTO> getByTitle(@RequestParam(value = "title",required = true) String title) throws Exception{
        String decodedSearchString = URLDecoder.decode(title, ENCODING_UTF_8);
        logger().info("Get book by title like '{}'",decodedSearchString);
        return bookService.findByTitleLike(decodedSearchString);
    }


    @RequestMapping(
            method = RequestMethod.GET,
            produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<BookDTO> getAll() {
        logger().info("Get all books");
        return bookService.findAll();
    }


    @RequestMapping(
            method = RequestMethod.POST,
            produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Void> createBook(@RequestBody @Valid BookDTO book) {
        logger().info("Create book {}",book);

        String isbn = bookService.createNew(book);

        HttpHeaders headers = new HttpHeaders();
        URI location = linkTo(methodOn(this.getClass()).getByIsbn(isbn)).toUri();
        logger().debug("Set header location to: {} ", location);
        headers.setLocation(location);

        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{isbn}",
            method = RequestMethod.PUT,
            produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void updateBook(@PathVariable String isbn, @RequestBody @Valid BookDTO book) {
        logger().info("Update book with isbn '{}' to {}",isbn, book);
        bookService.update(isbn,book);
    }


}
