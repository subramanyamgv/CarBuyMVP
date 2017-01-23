package app.subbu.carbuy.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import app.subbu.carbuy.R;
import app.subbu.carbuy.activity.EntitySelectionListener;
import app.subbu.carbuy.activity.MainTypesDialogActivity;
import app.subbu.carbuy.adapter.MainTypesListAdapter;
import app.subbu.carbuy.entity.Manufacturer;
import app.subbu.carbuy.entity.Model;
import app.subbu.carbuy.injector.component.CarSelectionComponent;
import app.subbu.mvp.model.MainTypes;
import app.subbu.mvp.presenter.MainTypesPresenter;
import app.subbu.mvp.view.MainTypesView;
import app.subbu.repository.Repository;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Subramanyam on 22-Jan-2017.
 */

public class MainTypesFragment extends Fragment implements MainTypesView,
        BaseQuickAdapter.RequestLoadMoreListener {

    public static final String TAG = "MainTypesFragment";

    @Inject
    MainTypesPresenter mPresenter;

    @BindView(R.id.list)
    RecyclerView mRecyclerView;

    private EntitySelectionListener<Model> mSelectionListener;
    private MainTypesListAdapter mAdapter;
    private Manufacturer mManufacturer;
    private int currentPage = 0;

    public static MainTypesFragment newInstance() {
        return new MainTypesFragment();
    }

    public static void start(int contentViewId, FragmentManager fragmentManager, Manufacturer manufacturer) {
        MainTypesFragment fragment = newInstance();
        fragment.setManufacturer(manufacturer);
        fragmentManager.beginTransaction().replace(contentViewId, fragment,
                TAG).commit();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectDependencies();
        mPresenter.onCreate();
    }

    private void injectDependencies() {

        if (getActivity() instanceof MainTypesDialogActivity) {
            MainTypesDialogActivity activity = (MainTypesDialogActivity) getActivity();
            CarSelectionComponent carSelectionComponent = activity.getCarSelectionComponent();
            carSelectionComponent.inject(this);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_car_types, container, false);
        ButterKnife.bind(this, view);

        //Init Adapter
        mAdapter = new MainTypesListAdapter(null);

        //Init List
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mAdapter);

        //Init Presenter
        mPresenter.attachView(this);

        //Set Load more call back
        mAdapter.setOnLoadMoreListener(this);

        mRecyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                Model model = (Model) adapter.getItem(position);
                mSelectionListener.onEntitySelected(model);
            }
        });

        return view;
    }

    @Override
    public void onLoadMoreRequested() {
        mPresenter.getMainTypes(mManufacturer.getManufacturerId(),
                currentPage + 1, Repository.DEFAULT_PAGE_SIZE);
    }

    @Override
    public void onStart() {
        super.onStart();
        mPresenter.onStart();
        mPresenter.getMainTypes(mManufacturer.getManufacturerId(), 0, Repository.DEFAULT_PAGE_SIZE);
    }

    @Override
    public void onStop() {
        super.onStop();
        mPresenter.onStop();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mSelectionListener = (EntitySelectionListener)getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString() + " should implement EntitySelectionListener");
        }
    }

    public void addMainTypes(MainTypes mainTypes) {

        List<Model> data = new LinkedList<>();

        int itemType = 0;

        for (Map.Entry<String, String> entry : mainTypes.getWkda().entrySet()) {

            Model model = new Model(itemType, entry.getKey(), entry.getValue());
            data.add(model);

            itemType = itemType ^ 1;
        }

        currentPage = mainTypes.getPage();

        mAdapter.addData(data);

        if (currentPage == mainTypes.getTotalPageCount())
            mAdapter.loadMoreEnd(true);
        else
            mAdapter.loadMoreComplete();
    }

    @Override
    public void showLoading() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                mAdapter.setEmptyView(R.layout.loading_view,
                        (ViewGroup) mRecyclerView.getParent());
            }
        };

        getActivity().runOnUiThread(runnable);
    }

    @Override
    public void showEmpty() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                mAdapter.setEmptyView(R.layout.empty_view,
                        (ViewGroup) mRecyclerView.getParent());
            }
        };

        getActivity().runOnUiThread(runnable);
    }

    @Override
    public void showMainTypes(final MainTypes mainTypes) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                addMainTypes(mainTypes);
            }
        };

        getActivity().runOnUiThread(runnable);
    }

    @Override
    public void showError(Throwable e) {

    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.mManufacturer = manufacturer;
    }
}
