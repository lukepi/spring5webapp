package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("Started in Bootstrap");

//        Publisher
        Publisher publisher = new Publisher();
        publisher.setName("Puffin");
        publisher.setAddressLine1("Puffin House");
        publisher.setCity("Chicago");
        publisher.setState("Illinois");
        publisherRepository.save(publisher);

//        Book1
        Book book1 = new Book("Domain Driven Design", "123123");
        Author auth1 = new Author("Eric", "Evans");

        book1.getAuthors().add(auth1);
        auth1.getBooks().add(book1);

        book1.setPublisher(publisher);
        publisher.getBooks().add(book1);

        authorRepository.save(auth1);
        bookRepository.save(book1);
        publisherRepository.save(publisher);

//        Book2
        Book book2 = new Book("J2EE Development without EJB", "3939459459");
        Author auth2 = new Author("Rod", "Johnson");

        book2.getAuthors().add(auth2);
        auth2.getBooks().add(book2);

        book2.setPublisher(publisher);
        publisher.getBooks().add(book2);

        authorRepository.save(auth2);
        bookRepository.save(book2);
        publisherRepository.save(publisher);


        System.out.println("Number of Books: "+ bookRepository.count());
        System.out.println("Number of Authors: "+ authorRepository.count());
        System.out.println("Number of Publishers: "+ publisherRepository.count());
        System.out.println("Number of Books in Publisher: " + publisher.getBooks().size());


    }
}
